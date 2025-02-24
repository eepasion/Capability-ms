package com.pragma.capability_mf.infrastructure.entrypoints;

import com.pragma.capability_mf.application.handler.CapabilityHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CapabilityRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(CapabilityHandlerImpl capabilityHandler){
        return route(POST("/capability"), capabilityHandler::saveCapability);
    }
}
