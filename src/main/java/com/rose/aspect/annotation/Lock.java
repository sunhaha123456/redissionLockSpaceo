package com.rose.aspect.annotation;

import java.lang.annotation.*;

/**
 * 方法上加入此注解，方法则会上锁
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Lock {

    /**
     * 锁名,如果此值为空,则使用方法名称作为锁名
     * @return
     */
    String value() default "";

    /**
     * 超时时间,默认30秒过期
     * @return
     */
    int leaseTime() default 30;
}
