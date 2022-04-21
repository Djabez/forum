package com.danli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author Mingyu Jin
 * @date  2021/5/3
 */
//Annotations for methods
@Target(ElementType.METHOD)
//Runtime usage
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLogger {
    String behavior() default "" ;
    String content() default "";

}
