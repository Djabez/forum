package com.danli.config;

import com.danli.interceptor.AccessLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuration to resolve cross-domain problems
 * cross-domain problem:When a browser requests resources from a web page of one domain name,
 * the domain name, port, or protocol are different
 *
 * @author Yicong Wang
 * @date  2022
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Autowired
    AccessLimitInterceptor accessLimitInterceptor;

    /**
     * 设置跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    /**
     * 设置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//         Multiple interceptors can be added, usually only one
//         addPathPatterns("/**") means all requests are intercepted
//        ExcludePathPatterns ("/base/index") rules out intercepting /base/index requests
//        Multiple interceptors can set the order. The smaller the value, the more preHandle is executed first, and postHandle and afterCompletion are executed later
//        The default value of order is 0. If only one interceptor is added, the value of setting order can not be displayed
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/**");

    }

}
