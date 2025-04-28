package com.Upload.Phu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Áp dụng cho tất cả endpoint
                        .allowedOrigins("*") // Cho phép tất cả nguồn (cẩn thận khi dùng)
                        .allowedMethods("*") // Cho phép tất cả phương thức HTTP (GET, POST, PUT, DELETE, OPTIONS, ...)
                        .allowedHeaders("*") // Cho phép tất cả headers
                        .allowCredentials(false); // Nếu không cần gửi cookie hoặc authentication
            }
        };
    }
}
