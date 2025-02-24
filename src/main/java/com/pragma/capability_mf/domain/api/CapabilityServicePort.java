package com.pragma.capability_mf.domain.api;

import com.pragma.capability_mf.domain.model.Capability;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapabilityServicePort {
    Mono<Capability> save(Capability capability);

    Flux<Capability> getAllCapabilitiesBy(int page, int size, String sortBy, String sort);

}
