package com.pragma.capability_mf.infrastructure.adapters.persistenceadapter;

import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.spi.CapabilityPersistencePort;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.mapper.CapabilityEntityMapper;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.repository.CapabilityRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CapabilityPersistenceAdapter implements CapabilityPersistencePort {
    private final CapabilityRepository capabilityRepository;
    private final CapabilityEntityMapper capabilityEntityMapper;
    @Override
    public Mono<Capability> save(Capability capability) {
        return capabilityRepository.save(capabilityEntityMapper.toEntity(capability))
                .map(capabilityEntityMapper::toModel);
    }

    @Override
    public Flux<Capability> getAllCapabilitiesBy(int page, int size, String sortBy, String sort) {
        int skip = (page -1) * size;
        if(sortBy == null) return capabilityRepository.findAllWithPagination(skip, size).map(capabilityEntityMapper::toModel);
        boolean descending = sort.equalsIgnoreCase("desc");
        int sortDirection = descending ? -1 : 1;
        if(sortBy.equalsIgnoreCase("name")) return capabilityRepository.findAllSortedByName(sortDirection, skip, size).map(capabilityEntityMapper::toModel);
        return capabilityRepository.findAllSortedByTecnhnologies(sortDirection, skip, size).map(capabilityEntityMapper::toModel);
    }

    @Override
    public Flux<Capability> getAllCapabilitiesById(List<String> ids) {
        return capabilityRepository.findAllById(ids).map(capabilityEntityMapper::toModel);
    }
}
