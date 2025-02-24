package com.pragma.capability_mf.domain.usecase;

import com.pragma.capability_mf.domain.api.CapabilityServicePort;
import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.spi.CapabilityPersistencePort;
import com.pragma.capability_mf.domain.validations.CapabilityValidations;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CapabilityUseCase implements CapabilityServicePort {
    private final CapabilityPersistencePort capabilityPersistencePort;
    @Override
    public Mono<Capability> save(Capability capability) {
        CapabilityValidations.validateCapability(capability);
        return capabilityPersistencePort.save(capability);
    }
}
