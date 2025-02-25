package com.pragma.capability_mf.domain.usecase;

import com.pragma.capability_mf.domain.api.CapabilityServicePort;
import com.pragma.capability_mf.domain.enums.ErrorMessages;
import com.pragma.capability_mf.domain.exceptions.BusinessException;
import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.model.CapabilityWithTechnologies;
import com.pragma.capability_mf.domain.model.Technology;
import com.pragma.capability_mf.domain.spi.CapabilityPersistencePort;
import com.pragma.capability_mf.domain.spi.TechnologyClientPort;
import com.pragma.capability_mf.domain.validations.CapabilityValidations;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RequiredArgsConstructor
public class CapabilityUseCase implements CapabilityServicePort {
    private final CapabilityPersistencePort capabilityPersistencePort;
    private final TechnologyClientPort technologyClientPort;
    @Override
    public Mono<Capability> save(Capability capability) {
        CapabilityValidations.validateCapability(capability);
        return getAllTechnologiesById(capability.technologies())
                .flatMap(technologies -> {
                    if(technologies.size() != capability.technologies().size()) {
                        return Mono.error(new BusinessException(ErrorMessages.CAPABILITY_TECHNOLOGY_NOT_FOUND));
                    }
                    return capabilityPersistencePort.save(capability);
                });
    }

    @Override
    public Flux<CapabilityWithTechnologies> getAllCapabilitiesBy(int page, int size, String sortBy, String sort) {
        CapabilityValidations.validateCapabilitySort(page, size, sortBy, sort);
        return capabilityPersistencePort.getAllCapabilitiesBy(page, size, sortBy, sort)
                .flatMap(this::mapToCapabilityWithTechnologies);
    }

    @Override
    public Flux<CapabilityWithTechnologies> getAllCapabilitiesById(List<String> ids) {
        return capabilityPersistencePort.getAllCapabilitiesById(ids).flatMap(this::mapToCapabilityWithTechnologies);
    }

    private Mono<List<Technology>> getAllTechnologiesById(List<Long> technologies) {
        return technologyClientPort.getAllTechnologiesById(technologies);
    }

    private Mono<CapabilityWithTechnologies> mapToCapabilityWithTechnologies(Capability capability) {
        return getAllTechnologiesById(capability.technologies())
                .map(technologies -> new CapabilityWithTechnologies(
                        capability.id(),
                        capability.name(),
                        capability.description(),
                        technologies
                ));
    }


}
