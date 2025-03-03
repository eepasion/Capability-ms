package com.pragma.capability_mf.domain.spi;

import com.pragma.capability_mf.domain.model.Capability;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityPersistencePort {
    Mono<Capability> save(Capability capability);
    Flux<Capability> getAllCapabilitiesBy(int page, int size,String sortBy,String sort);
    Flux<Capability> getAllCapabilitiesById(List<String> ids);
}
