package icu.rolin.easy.config;

import icu.rolin.easy.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${web.static.path}")
    private String staticPath;

    @Bean
    public UserInterceptor startUserInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行
         */
        registry.addInterceptor(startUserInterceptor()).addPathPatterns("/api/u") //先随便写一个 放行所有请求
                .excludePathPatterns(
                        "/api/user/login",
                        "/api/user/register",
                        "/api/tool/uni-variable",
                        "/api/user/forget-password",
                        "/**/**/upload-image",
                        "/files/**",
                        "/images/**",
                        "/favicon.ico"
                );

        super.addInterceptors(registry);
    }

//    静态资源映射器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("files/**","images/**").addResourceLocations(
                "file:"+staticPath);
    }


}
