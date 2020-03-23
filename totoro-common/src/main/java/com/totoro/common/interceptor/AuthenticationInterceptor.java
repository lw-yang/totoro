package com.totoro.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 验证用户 token
 *
 * @author lwyang  2020/3/8
 */

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final String AUTHENTICATION_TOKEN = "X-Totoro-Token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // NeedAuthentication verification
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            boolean isClassAuth = handlerMethod.getMethod().getDeclaringClass().isAnnotationPresent(NeedAuthentication.class);
            boolean isMethodAuth = handlerMethod.hasMethodAnnotation(NeedAuthentication.class);

            if (isClassAuth || isMethodAuth) {
                // refuse no token
                String token = request.getHeader(AUTHENTICATION_TOKEN);

                // TODO 潜在bug，应该在此处验证token是否过期，接口测试时某些接口会越权，但正常前端访问是没有问题
                // TODO 若在此处验证token有效期，会依赖于totoro-server模块，造成循环依赖，后期分离出用户鉴权中心即可解决此问题

                return !Objects.isNull(token);
            }
        }
        return true;
    }
}
