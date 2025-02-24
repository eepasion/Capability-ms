package com.pragma.capability_mf.domain.spi;

import com.pragma.capability_mf.domain.model.Technology;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyClientPort {
    Mono<List<Technology>> getAllTechnologiesById(List<Long> ids);
}
