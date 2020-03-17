package com.aaa.lee.repast.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Adam
 * @Description
 *      定义登录日志注解
 * @Parme:
 * @return : null
 * @Date: 2020/3/11 20:56
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLogAnnotation {
    /**
     * @Author Adam
     * @Description
     *      定义要执行操作的类型
     *          登录操作、下单操作、删除操作
     * @Parme: []
     * @return : java.lang.String
     * @Date: 2020/3/11 20:59
     */
    String operationType() default "";
    /**
     * @Author Adam
     * @Description
     *      定义要执行操作的名字：具体要执行的操作
     *          删除操作--->删除用户，清空购物车、删除商品
     * @Parme: []
     * @return : java.lang.String
     * @Date: 2020/3/11 21:01
     */
    String operationName() default "";
}
