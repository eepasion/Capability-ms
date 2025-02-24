package com.pragma.capability_mf.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CapabilityRequestDto {
    private String name;
    private String description;
    private String[] technologies;
}
