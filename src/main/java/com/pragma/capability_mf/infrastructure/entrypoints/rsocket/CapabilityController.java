package com.pragma.capability_mf.infrastructure.entrypoints.rsocket;

import com.pragma.capability_mf.application.dto.CapabilityTechnologiesDto;
import com.pragma.capability_mf.application.handler.CapabilityHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CapabilityController {
    private final CapabilityHandlerImpl capabilityHandler;
    @MessageMapping("get.capabilities")
    public Mono<List<CapabilityTechnologiesDto>> getCapabilitiesById(List<String> ids) {
        return capabilityHandler.getAllCapabilitiesById(ids).collectList();
    }


}
