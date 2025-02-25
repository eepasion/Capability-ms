package com.pragma.capability_mf.domain.api;

import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.model.CapabilityWithTechnologies;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityServicePort {
    Mono<Capability> save(Capability capability);

    Flux<CapabilityWithTechnologies> getAllCapabilitiesBy(int page, int size, String sortBy, String sort);
    Flux<CapabilityWithTechnologies> getAllCapabilitiesById(List<String> ids);


}
