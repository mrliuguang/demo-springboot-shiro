package com.hnsic.demospringbootshiro.config;

import com.hnsic.demospringbootshiro.componet.SimpleleHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    //    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //super.addViewControllers(registry);
//        registry.addViewController("ok").setViewName("success");
//    }
//
//    @Bean
//    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
//
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>(){
//
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                factory.setPort(8086);
//            }
//        };
//    }
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/unauth").setViewName("unauth");
            }

            @Override
            /**
             * 增加自己的简单拦截，拦截所有请求(并未作任何处理)，开放静态资源
             */
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new SimpleleHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/users/login", "/**/*.css",
                                "/**/*.js", "/**/*.png", "/**/*.jpg",
                                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
            }
        };
        return webMvcConfigurer;
    }
}
