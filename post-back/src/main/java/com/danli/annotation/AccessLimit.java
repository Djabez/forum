package com.danli.annotation;

/**
 * @author: Mingyu Jin
 * @date: 2022/3/20
 */
/**In this way, visitors are limited and mingyu Forum is prevented from being visited in a short period of time, resulting in a crash*/
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    /**
     * Limiting period (seconds)
     */
    int seconds();

    /**
     * Limit the number of times specified in the cycle
     */
    int maxCount();

    /**
     * A message prompt when a restriction is triggered
     */
    String msg() default "Operation frequency too high";

}
