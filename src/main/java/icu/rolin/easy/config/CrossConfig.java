package icu.rolin.easy.config;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CrossConfig implements WebMvcConfigurer {

    public void addCrossMappings(CorsRegistry corsRegistry){
        System.out.println("----------");
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .exposedHeaders(HttpHeaders.SET_COOKIE)
                .maxAge(3600);
    }

}