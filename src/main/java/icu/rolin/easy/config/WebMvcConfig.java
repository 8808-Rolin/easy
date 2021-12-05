package icu.rolin.easy.config;

import icu.rolin.easy.interceptor.UserInterceptor;
import icu.rolin.easy.interceptor.ZoneInterceptor;
import icu.rolin.easy.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private String staticPath = Constant.FILE_PATH;

    @Bean
    public UserInterceptor startUserInterceptor(){
        return new UserInterceptor();
    }

    /**
     * 拦截器按照顺序执行
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(startUserInterceptor()).addPathPatterns("/api/**") //先随便写一个 放行所有请求
                .excludePathPatterns(
                        "/api/user/**",
                        "/api/tool/**",
                        "/api/bbs/get-simple-notice",
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
