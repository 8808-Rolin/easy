package icu.rolin.easy.config;

import icu.rolin.easy.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public UserInterceptor startUserInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行
         */
        registry.addInterceptor(startUserInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login");
        super.addInterceptors(registry);
    }

}
