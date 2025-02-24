package com.pragma.capability_mf.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessages {
    INTERNAL_ERROR(500, "Hubo un error, por favor intente nuevamente mas tarde."),
    CAPABILITY_TECHNOLOGY_MIN_SIZE(400, "La capacidad debe tener minimo 3 tecnologias."),
    CAPABILITY_TECHNOLOGY_MAX_SIZE(400, "La capacidad debe tener maximo 20 tecnologias."),
    CAPABILITY_TECHNOLOGY_REPEATED(400, "La capacidad no debe tener tecnologias repetidas."),
    CAPABILITY_TECHNOLOGY_NOT_FOUND(400, "Una de las tecnologias no fue encontrada. por favor asegurese de crear todas las tecnologias."),
    CAPABILITY_NEEDS_NAME(400, "La capacidad debe tener un nombre."),
    CAPABILITY_NEEDS_DESCRIPTION(400, "La capacidad debe tener una descripcion."),
    CAPABILITY_NAME_SIZE(400, "El nombre de la capacidad debe tener un maximo de 50 caracteres."),
    CAPABILITY_DESCRIPTION_SIZE(400, "La descripcion de la capacidad debe tener un maximo de 90 caracteres."),
    ;

    private final Number code;
    private final String message;

    public static ErrorMessages fromException(Throwable ex) {
        for (ErrorMessages error : values()) {
            if (ex.getMessage().contains(error.message)) {
                return error;
            }
        }
        return INTERNAL_ERROR;
    }
}
