package com.pragma.capability_mf.application.mapper;

import com.pragma.capability_mf.application.dto.CapabilityRequestDto;
import com.pragma.capability_mf.application.dto.CapabilityTechnologiesDto;
import com.pragma.capability_mf.domain.model.Capability;
import com.pragma.capability_mf.domain.model.CapabilityWithTechnologies;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapabilityMapper {
    @Mapping(target = "id", ignore = true)
    Capability toModel(CapabilityRequestDto capabilityDto);

    CapabilityTechnologiesDto toResponseDto(CapabilityWithTechnologies capability);
}
