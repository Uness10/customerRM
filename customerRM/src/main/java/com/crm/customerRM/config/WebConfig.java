package com.crm.customerRM.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-cors");
        registry.addMapping("/api/**") // Allow access to all endpoints starting with /api/
                .allowedOrigins("*") // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*"); // Allow any headers
    }
}
