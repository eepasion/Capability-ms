package com.pragma.capability_mf.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessMessages {
    CAPABILITY_CREATED("Capacidad creada con exito.");
    private final String message;
}
