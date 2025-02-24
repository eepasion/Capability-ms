package com.pragma.capability_mf.domain.usecase;

import com.pragma.capability_mf.domain.api.CapabilityServicePort;
import com.pragma.capability_mf.domain.enums.ErrorMessages;
import com.pragma.capability_mf.domain.exceptions.BusinessException;
import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.spi.CapabilityPersistencePort;
import com.pragma.capability_mf.domain.spi.TechnologyClientPort;
import com.pragma.capability_mf.domain.validations.CapabilityValidations;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class CapabilityUseCase implements CapabilityServicePort {
    private final CapabilityPersistencePort capabilityPersistencePort;
    private final TechnologyClientPort technologyClientPort;
    @Override
    public Mono<Capability> save(Capability capability) {
        CapabilityValidations.validateCapability(capability);
        return technologyClientPort.getAllTechnologiesById(capability.technologies())
                .flatMap(technologies -> {
                    if(technologies.size() != capability.technologies().size()) {
                        return Mono.error(new BusinessException(ErrorMessages.CAPABILITY_TECHNOLOGY_NOT_FOUND));
                    }
                    return capabilityPersistencePort.save(capability);
                });
    }
}
