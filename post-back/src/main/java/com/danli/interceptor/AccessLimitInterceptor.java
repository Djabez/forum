package com.danli.interceptor;

import cn.hutool.json.JSONObject;
import com.danli.annotation.AccessLimit;
import com.danli.common.lang.Result;
import com.danli.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 *
 * @author Mingyu
 * @date 2022.04.05
 */
@Component
public class AccessLimitInterceptor  implements HandlerInterceptor  {
    @Autowired
    RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            //Method with no access control annotations, passes directly
            if (accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            String ip = request.getHeader("x-forwarded-for");;
            String method = request.getMethod();
            String requestURI = request.getRequestURI();
            String redisKey = ip + ":" + method + ":" + requestURI;
            Integer count = (Integer) redisService.getObjectByValue(redisKey);

            if (count == null) {
                //For the first visit within the specified period, deposit in REDis
                redisService.saveObjectToValue(redisKey, 1);
                redisService.expire(redisKey, seconds);
            } else {
                if (count >= maxCount) {
                    // The number of times the access limit was exceeded
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Result result = Result.fail(403, accessLimit.msg(),null);
                    out.write(String.valueOf(new JSONObject(result)));
                    out.flush();
                    out.close();
                    return false;
                } else {
                    //The number of times the access limit was  not exceeded
                    redisService.incrementByKey(redisKey, 1);
                }
            }
        }
        return true;
    }
}
