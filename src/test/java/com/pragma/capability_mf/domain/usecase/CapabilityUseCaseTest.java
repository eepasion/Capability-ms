package com.pragma.capability_mf.domain.usecase;

import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.model.Technology;
import com.pragma.capability_mf.domain.spi.CapabilityPersistencePort;
import com.pragma.capability_mf.domain.spi.TechnologyClientPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapabilityUseCaseTest {

    @Mock
    CapabilityPersistencePort capabilityPersistencePort;
    @Mock
    TechnologyClientPort technologyClientPort;

    @InjectMocks
    CapabilityUseCase capabilityUseCase;

    @Test
    void saveCapabilitySuccessful() {
        Technology technology = new Technology(1L, "java");
        Technology technology2 = new Technology(2L, "javascript");
        Technology technology3 = new Technology(3L, "aws");
        List<Technology> existingTechnologies = List.of(technology, technology2, technology3);
        List<Long> technologyIds = List.of(1L,2L,3L);
        Capability capability = new Capability("id", "name", "description", technologyIds);

        when(technologyClientPort.getAllTechnologiesById(technologyIds)).thenReturn(Mono.just(existingTechnologies));
        when(capabilityPersistencePort.save(capability)).thenReturn(Mono.just(capability));

        StepVerifier.create(capabilityUseCase.save(capability))
                .expectNext(capability)
                .verifyComplete();
        verify(capabilityPersistencePort,times(1)).save(capability);
        verify(technologyClientPort,times(1)).getAllTechnologiesById(technologyIds);
    }

}