package com.danli.aspect;

import cn.hutool.json.JSONObject;
import com.danli.annotation.VisitLogger;
import com.danli.common.lang.Result;
import com.danli.config.RedisKeyConfig;
import com.danli.entity.Blog;
import com.danli.entity.VisitLog;
import com.danli.entity.Visitor;
import com.danli.service.RedisService;
import com.danli.service.VisitLogService;
import com.danli.service.VisitorService;
import com.danli.util.IpAddressUtils;
import com.danli.util.UserAgentUtils;
import com.google.common.base.Verify;
import org.apache.commons.collections4.Get;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;


/**
 *
 *
 * @author Yicong wang
 * @date  2022
 */
@Component
@Aspect
public class VisitLogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    VisitLogService visitLogService;
    @Autowired
    VisitorService visitorService;
    @Autowired
    UserAgentUtils userAgentUtils;
    @Autowired
    RedisService redisService;


    ThreadLocal<Long> currentTime = new ThreadLocal<>();


    /**
     * Configuration pointcut
     */
    @Pointcut("@annotation(visitLogger)")
    public void log(VisitLogger visitLogger){}

    /**
     * Configuring surround Notification
     *
     * @param joinPoint
	 * @return Returns the result of method execution
	 */
    //On the basis of database establishment and front-end preliminary function realization, I have completed the login and logout function of mingyu forum
    @Around("log(visitLogger)")
    public Object logAround(ProceedingJoinPoint joinPoint,VisitLogger visitLogger) throws Throwable {

        currentTime.set(System.currentTimeMillis());
           //Get the request object
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
           //Let the target method execute to get the result returned
        Object result = joinPoint.proceed();
        int times = (int) (System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        //Verify the visitor ID
        String identification = checkIdentification(request);
          //Asynchronously save to database
        saveVisitLog(joinPoint, visitLogger, request, result, times, identification);

        return result;
    }



    void saveVisitLog(){

    }



    /**
     * Asynchronously set the VisitLogger object properties and save them to the database
     *
     * @param joinPoint
     * @param visitLogger
     * @param result
     * @param times
     * @return
     */
    @Async
    void saveVisitLog(ProceedingJoinPoint joinPoint, VisitLogger visitLogger, HttpServletRequest request, Object result,
                               int times, String identification) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String behavior = visitLogger.behavior();
        String content = visitLogger.content();
        String ip = request.getHeader("x-forwarded-for");
       // Gets the parameter name and parameter value


        String ipSource = IpAddressUtils.getCityInfo(ip);
        String userAgent = request.getHeader("User-Agent");
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(userAgent);
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");

        //Determine the access content based on the access content and the returned result and make remarks


        Map<String, Object> requestParams = new LinkedHashMap<>();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if( args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse || args[i] instanceof MultipartFile){
                continue;
            }
            requestParams.put(parameterNames[i], args[i]);
        }

        Map<String, String> map = judgeBehavior(behavior, content, requestParams, result);
        VisitLog log = new VisitLog(null,identification, uri, method, new JSONObject(requestParams).toString(), behavior, map.get("content"),map.get("remark"), ip,ipSource,os,browser,LocalDateTime.now(),times, userAgent);

        visitLogService.saveOrUpdate(log);
    }



    /**
     * Set the access content or remarks based on the access behavior
     *
     * @param behavior
     * @param content
     * @param requestParams
     * @param result
     * @return Returns a map with contents and remarks for the primary key
     */
    private Map<String, String> judgeBehavior(String behavior, String content, Map<String, Object> requestParams, Object result) {
        Map<String, String> map = new HashMap<>();
        String remark = "";
        if (behavior.equals("Access the page") && (content.equals("Home page"))) {
            int pageNum = (int) requestParams.get("currentPage");
            remark = "" + pageNum + "";
        } else if (behavior.equals("View the blog")) {
            Result res = (Result) result;
            if (res.getCode() == 200) {
                Blog blog = (Blog) res.getData();
                String title = blog.getTitle();
                content = title;
                remark = "The article title：" + title;
            }
        } else if (behavior.equals("Search the blog")) {
            Result res = (Result) result;
            if (res.getCode() == 200) {
                String query = (String) requestParams.get("queryString");
                content = query;
                remark = "Search content：" + query;
            }
        } else if (behavior.equals("Check the classification")) {
            String categoryName = (String) requestParams.get("typeName");
            int pageNum = (int) requestParams.get("currentPage");
            content = categoryName;
            remark = "Category name：" + categoryName + "，" + pageNum + "";
        } else if (behavior.equals("Click the friend chain")) {
            String nickname = (String) requestParams.get("nickname");
            content = nickname;
            remark = "Name of the friend chain：" + nickname;
        }
        map.put("remark", remark);
        map.put("content", content);
        return map;
    }




    /**
     * Verify visitor ID
     *
     * @param request
     * @return Visitor ID UUID
     */
    private String checkIdentification(HttpServletRequest request) {
        String identification = request.getHeader("identification");
        if (identification == null) {
            //On the first access, the UUID is issued and saved to the database and Redis
            identification =   UUID.randomUUID().toString();
            saveUUID(identification,request);
        } else {
            //Verify that a UUID exists in Redis
            boolean redisHas = redisService.hasValueInSet(RedisKeyConfig.IDENTIFICATION_SET, identification);

            //Redis中不存在uuid
            if (!redisHas) {
                //Uuid does not exist in Redis
                boolean mysqlHas = visitorService.hasUUID(identification);
                if (mysqlHas) {
                    //The database exists and saved to Redis
                    redisService.saveValueToSet("identificationSet", identification);
                    //Update last access time and PV
                    updateVistor(identification);

                } else {
                    //The database does not exist. Issue a new UUID
                    identification =   UUID.randomUUID().toString();
                    //Asynchronous save
                    saveUUID(identification,request);
                }
            }
            else{
                //Update last time and PV
                updateVistor(identification);
            }
        }
        return identification;
    }





    @Async
    void updateVistor(String identification) {
        //Update last access time and PV
        Visitor visitor = visitorService.getVisitorByUuid(identification);
        visitor.setPv(visitor.getPv()+1);
        visitor.setLastTime(LocalDateTime.now());
        visitorService.saveOrUpdate(visitor);
    }


    /**
     * Asynchronously save UUID to database and Redis
     *
     * @param request
     * @return UUID
     */
    @Async
    void saveUUID(String uuid,HttpServletRequest request) {

        //Save to Redis
        redisService.saveValueToSet(RedisKeyConfig.IDENTIFICATION_SET, uuid);
        //Get the response object
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        //Add the visitor identifier UUID to the response header
        response.addHeader("identification", uuid);
        //Expose custom headers for page resources to use
        response.addHeader("Access-Control-Expose-Headers", "identification");

        //Get basic information about visitors
        String ip = request.getHeader("x-forwarded-for");

        String userAgent = request.getHeader("User-Agent");
        String ipSource = IpAddressUtils.getCityInfo(ip);
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(userAgent);
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        Visitor visitor = new Visitor(null,uuid, ip,ipSource,os,browser,LocalDateTime.now(),LocalDateTime.now(),1, userAgent);
        //Save to database
        visitorService.saveOrUpdate(visitor);
    }
}
