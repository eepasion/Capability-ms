package com.pragma.capability_mf.application.handler;

import com.pragma.capability_mf.application.dto.CapabilityRequestDto;
import com.pragma.capability_mf.application.mapper.CapabilityMapper;
import com.pragma.capability_mf.domain.api.CapabilityServicePort;
import com.pragma.capability_mf.domain.enums.SuccessMessages;
import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.model.CapabilityWithTechnologies;
import com.pragma.capability_mf.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@ExtendWith(MockitoExtension.class)
class CapabilityHandlerImplTest {

    @Mock
    private CapabilityMapper capabilityMapper;
    @Mock
    private CapabilityServicePort capabilityServicePort;
    @InjectMocks
    private CapabilityHandlerImpl capabilityHandler;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToRouterFunction(
                route(POST("/capability"), capabilityHandler::saveCapability)
                        .andRoute(GET("/capability"), capabilityHandler::getAllCapabilitiesBy)
        ).build();
    }

    @Test
    void saveCapability() {
        Capability capability = new Capability("id", "name", "description", List.of(1L,2L,3L));
        CapabilityRequestDto capabilityRequestDto = new CapabilityRequestDto("name", "description", new Long[]{1L,2L,3L});
        when(capabilityMapper.toModel(any(CapabilityRequestDto.class))).thenReturn(capability);
        when(capabilityServicePort.save(capability)).thenReturn(Mono.just(capability));

        webTestClient.post().uri("/capability").bodyValue(capabilityRequestDto).exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.message").isEqualTo(SuccessMessages.CAPABILITY_CREATED.getMessage());
    }

    @Test
    void getAllCapabilitiesBy() {
        Technology technology = new Technology(1L, "java");
        CapabilityWithTechnologies capability = new CapabilityWithTechnologies("id", "name", "description", List.of(technology));
        when(capabilityServicePort.getAllCapabilitiesBy(anyInt(), anyInt(), anyString(), anyString())).thenReturn(Flux.just(capability));

        webTestClient.get().uri(uriBuilder -> uriBuilder.path("/capability")
                                .queryParam("page", 1)
                                .queryParam("size", 10)
                                .queryParam("sortBy", "name")
                                .queryParam("sort", "asc")
                .build()).exchange()
                .expectStatus().isOk();
    }
}