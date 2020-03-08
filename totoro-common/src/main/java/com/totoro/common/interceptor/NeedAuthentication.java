package com.totoro.common.interceptor;

import java.lang.annotation.*;

/**
 * 使用此注解的方法或类需要 token 验证
 *
 * @author lwyang  2020/3/8
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedAuthentication {

}
