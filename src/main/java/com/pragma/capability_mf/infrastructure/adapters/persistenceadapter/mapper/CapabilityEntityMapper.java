package com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.mapper;

import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.infrastructure.adapters.persistenceadapter.entity.CapabilityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CapabilityEntityMapper {
    Capability toModel(CapabilityEntity capabilityEntity);
    CapabilityEntity toEntity(Capability capability);
}
