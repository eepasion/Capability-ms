package com.pragma.capability_mf.application.handler;

import com.pragma.capability_mf.application.dto.CapabilityRequestDto;
import com.pragma.capability_mf.application.mapper.CapabilityMapper;
import com.pragma.capability_mf.domain.api.CapabilityServicePort;
import com.pragma.capability_mf.domain.enums.SuccessMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CapabilityHandlerImpl {
    private final CapabilityMapper capabilityMapper;
    private final CapabilityServicePort capabilityServicePort;

    public Mono<ServerResponse> saveCapability(ServerRequest request) {
        return request.bodyToMono(CapabilityRequestDto.class)
                .map(dto->{
                    dto.setName(dto.getName().trim());
                    dto.setDescription(dto.getDescription().trim());
                    return dto;
                })
                .flatMap(dto -> capabilityServicePort.save(capabilityMapper.toModel(dto)))
                .flatMap(saved->{
                    Map<String,String> response = Map.of("message", SuccessMessages.CAPABILITY_CREATED.getMessage());
                    return ServerResponse.status(HttpStatus.CREATED).bodyValue(response);
                });
    }

    public Mono<ServerResponse> getAllCapabilitiesBy(ServerRequest request) {
        int page = Integer.parseInt(request.queryParam("page").orElse("1"));
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        String sortBy = request.queryParam("sortBy").orElse(null);
        String sort = request.queryParam("sort").orElse(null);
        return capabilityServicePort.getAllCapabilitiesBy(page, size, sortBy, sort)
                .collectList()
                .flatMap(capabilities -> ServerResponse.ok().bodyValue(capabilities));
    }
}
