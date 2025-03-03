package com.pragma.capability_mf.infrastructure.adapters.persistenceadapter;

import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.entity.CapabilityEntity;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.mapper.CapabilityEntityMapper;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.repository.CapabilityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapabilityPersistenceAdapterTest {
    @Mock
    private CapabilityRepository capabilityRepository;
    @Mock
    private CapabilityEntityMapper capabilityEntityMapper;

    @InjectMocks
    private CapabilityPersistenceAdapter capabilityPersistenceAdapter;

    @Test
    void testSaveSuccessFul() {
        Capability capability = new Capability("id", "name", "description", List.of(1L,2L,3L));
        CapabilityEntity capabilityEntity = new CapabilityEntity("id", "name", "description", new Long[]{1L,2L,3L});
        when(capabilityRepository.save(any())).thenReturn(Mono.just(capabilityEntity));
        when(capabilityEntityMapper.toEntity(any())).thenReturn(capabilityEntity);
        when(capabilityEntityMapper.toModel(any())).thenReturn(capability);

        StepVerifier.create(capabilityPersistenceAdapter.save(capability))
                .expectNext(capability)
                .verifyComplete();
        verify(capabilityRepository, times(1)).save(any());
        verify(capabilityEntityMapper, times(1)).toEntity(any());
        verify(capabilityEntityMapper, times(1)).toModel(any());
    }

    @Test
    void getAllCapabilitiesByShouldCallsPaginationWhenSortByIsNull() {
        Capability capability = new Capability("id", "name", "description", List.of(1L,2L,3L));
        CapabilityEntity capabilityEntity = new CapabilityEntity("id", "name", "description", new Long[]{1L,2L,3L});
        when(capabilityRepository.findAllWithPagination(anyInt(), anyInt())).thenReturn(Flux.just(capabilityEntity));
        when(capabilityEntityMapper.toModel(any())).thenReturn(capability);

        StepVerifier.create(capabilityPersistenceAdapter.getAllCapabilitiesBy(1, 10, null, null))
                .expectNext(capability)
                .verifyComplete();
        verify(capabilityRepository, times(1)).findAllWithPagination(anyInt(), anyInt());
        verify(capabilityEntityMapper, times(1)).toModel(any());
    }

    @Test
    void getAllCapabilitiesByShouldOrderByName() {
        Capability capability = new Capability("id", "name", "description", List.of(1L,2L,3L));
        CapabilityEntity capabilityEntity = new CapabilityEntity("id", "name", "description", new Long[]{1L,2L,3L});
        when(capabilityRepository.findAllSortedByName(anyInt(), anyInt(), anyInt())).thenReturn(Flux.just(capabilityEntity));
        when(capabilityEntityMapper.toModel(any())).thenReturn(capability);

        StepVerifier.create(capabilityPersistenceAdapter.getAllCapabilitiesBy(1, 10, "name", "asc"))
                .expectNext(capability)
                .verifyComplete();
        verify(capabilityRepository, times(1)).findAllSortedByName(anyInt(), anyInt(), anyInt());
        verify(capabilityEntityMapper, times(1)).toModel(any());
    }

    @Test
    void getAllCapabilitiesByShouldOrderByTechnology() {
        Capability capability = new Capability("id", "name", "description", List.of(1L,2L,3L));
        CapabilityEntity capabilityEntity = new CapabilityEntity("id", "name", "description", new Long[]{1L,2L,3L});
        when(capabilityRepository.findAllSortedByTecnhnologies(anyInt(), anyInt(), anyInt())).thenReturn(Flux.just(capabilityEntity));
        when(capabilityEntityMapper.toModel(any())).thenReturn(capability);

        StepVerifier.create(capabilityPersistenceAdapter.getAllCapabilitiesBy(1, 10, "tech", "asc"))
                .expectNext(capability)
                .verifyComplete();
        verify(capabilityRepository, times(1)).findAllSortedByTecnhnologies(anyInt(), anyInt(), anyInt());
        verify(capabilityEntityMapper, times(1)).toModel(any());
    }
}