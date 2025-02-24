package com.pragma.capability_mf.domain.spi;

import com.pragma.capability_mf.domain.model.Capability;
import reactor.core.publisher.Mono;

public interface CapabilityPersistencePort {
    Mono<Capability> save(Capability capability);

}
