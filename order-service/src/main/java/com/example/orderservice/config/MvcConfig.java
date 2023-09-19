package com.example.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class MvcConfig {
    @Bean
    PageableHandlerMethodArgumentResolverCustomizer pageableHandlerMethodArgumentResolverCustomizer() {
        return pageableResolver -> {
            pageableResolver.setMaxPageSize(1000);
            pageableResolver.setOneIndexedParameters(true);
        };
    }
}