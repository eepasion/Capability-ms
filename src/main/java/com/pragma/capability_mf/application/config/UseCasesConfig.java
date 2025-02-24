package com.pragma.capability_mf.application.config;

import com.pragma.capability_mf.domain.api.CapabilityServicePort;
import com.pragma.capability_mf.domain.spi.CapabilityPersistencePort;
import com.pragma.capability_mf.domain.usecase.CapabilityUseCase;
import com.pragma.capability_mf.infrastructure.adapters.clientadapter.TechnologyClientAdapter;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.CapabilityPersistenceAdapter;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.mapper.CapabilityEntityMapper;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.repository.CapabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCasesConfig {
    private final CapabilityRepository capabilityRepository;
    private final CapabilityEntityMapper capabilityEntityMapper;
    private final TechnologyClientAdapter technologyClientAdapter;

    @Bean
    public CapabilityPersistencePort capabilityPersistencePort(){
        return new CapabilityPersistenceAdapter(capabilityRepository, capabilityEntityMapper);
    }

    @Bean
    public CapabilityServicePort capabilityServicePort(){
        return new CapabilityUseCase(capabilityPersistencePort(), technologyClientAdapter);
    }
}
