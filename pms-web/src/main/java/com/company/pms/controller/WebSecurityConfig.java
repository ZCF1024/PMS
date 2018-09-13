package com.company.pms.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zcf
 * @Create 2018/7/20 17:04
 * @Desc Interceptor(拦截器)
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SecurityInterceptor getSecurityInterceptor(){
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // addInterceptor 通过此方法添加拦截器
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置 不拦截 ("/login" , "/") 的请求
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/**");

        // 拦截配置, 拦截 ("/employee/**") 的请求
//        addInterceptor.addPathPatterns("/employee/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        /***
         *  预处理回调方法，实现处理器的预处理（如登录检查）
         * @param request
         * @param response
         * @param handler 响应的处理器（Controller）；
         * @return  true表示继续流程（如调用下一个拦截器或处理器）；
         *          false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，
         *               此时我们需要通过response来产生响应；
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response
                , Object handler) throws Exception {
            if(request.getSession().getAttribute(("user")) == null){
                response.sendRedirect("/login");
                return false;
            }
            return true;
        }
    }
}