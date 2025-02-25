package com.pragma.capability_mf.infrastructure.entrypoints;

import com.pragma.capability_mf.application.dto.CapabilityRequestDto;
import com.pragma.capability_mf.application.handler.CapabilityHandlerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CapabilityRouterConfig {
    @RouterOperations({

            @RouterOperation(
                    path = "/capability",
                    beanClass = CapabilityHandlerImpl.class,
                    beanMethod = "saveCapability",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            operationId = "opSaveCapability",
                            summary = "Guarda una nueva capacidad",
                            requestBody = @RequestBody(
                                    description = "Estructura de una capacidad",
                                    required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = CapabilityRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "201", description = "Capacidad creada"),
                                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/capability", beanClass = CapabilityHandlerImpl.class, beanMethod = "getAllCapabilitiesBy",
                    method = RequestMethod.GET,
                    operation = @Operation(
                            operationId = "opGetAllTechnologies",
                            summary = "Obtiene todas las capacidades permitiendo ordenar por parametros",
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "size", required = false,description = "Cantidad de elementos por página"),
                                    @Parameter(in = ParameterIn.QUERY, name = "page", required = false,description = "Número de página"),
                                    @Parameter(in = ParameterIn.QUERY, name = "sort", required = false,description = "Orden ascendente o descendente el valor es asc o desc"),
                                    @Parameter(in = ParameterIn.QUERY, name = "sortBy", required = false, description = "Ordenar por name o tech")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Capacidades obtenidas exitosamente"),
                                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
                            }
                    )
            )
    })
    @Bean
    public RouterFunction<ServerResponse> routerFunction(CapabilityHandlerImpl capabilityHandler){
        return route(POST("/capability"), capabilityHandler::saveCapability)
                .andRoute(GET("/capability"), capabilityHandler::getAllCapabilitiesBy);
    }
}
