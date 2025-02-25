package com.pragma.capability_mf.infrastructure.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("Capability")
                .pathsToMatch("/capability/**")
                .build();
    }
}
