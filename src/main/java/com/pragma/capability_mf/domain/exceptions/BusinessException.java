package com.pragma.capability_mf.domain.exceptions;

import com.pragma.capability_mf.domain.enums.ErrorMessages;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorMessages errorMessages;
    public BusinessException(ErrorMessages errorMessages) {
        super(errorMessages.getMessage());
        this.errorMessages = errorMessages;
    }
}
