package com.pragma.capability_mf.infrastructure.adapters.persistenceadapter;

import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.spi.CapabilityPersistencePort;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.mapper.CapabilityEntityMapper;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.repository.CapabilityRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CapabilityPersistenceAdapter implements CapabilityPersistencePort {
    private final CapabilityRepository capabilityRepository;
    private final CapabilityEntityMapper capabilityEntityMapper;
    @Override
    public Mono<Capability> save(Capability capability) {
        return capabilityRepository.save(capabilityEntityMapper.toEntity(capability)).map(capabilityEntityMapper::toModel);
    }
}
