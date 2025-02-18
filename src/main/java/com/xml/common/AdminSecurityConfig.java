//package com.xml.common;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.tomcat.util.descriptor.web.LoginConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//@Configuration
//public class AdminSecurityConfig extends WebMvcConfigurerAdapter {
//
//
//    public final static String SESSION_KEY = "admin";
//
//    @Bean
//    public SecurityInterceptor getSecurityInterceptor() {
//        return new SecurityInterceptor();
//    }
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//        //排除的路径
//        //拦截所有路径
//        addInterceptor.excludePathPatterns("/login");
//        addInterceptor.excludePathPatterns("/");
//        addInterceptor.addPathPatterns("/**");
//    }
//
//    private class SecurityInterceptor extends HandlerInterceptorAdapter {
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//            HttpSession session = request.getSession();
//
//            //判断是否已有该用户登录的session
//            if (session.getAttribute(SESSION_KEY) != null) {
//                return true;
//            }
//            //跳转到登录页
//            response.sendRedirect("/");
//            return false;
//        }
//    }
//
//}
