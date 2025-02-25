package com.pragma.capability_mf.application.dto;

import com.pragma.capability_mf.domain.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class CapabilityTechnologiesDto {
    String id;
    String name;
    List<Technology> technologies;
}
