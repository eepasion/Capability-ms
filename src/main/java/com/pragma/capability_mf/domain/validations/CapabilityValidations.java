package com.pragma.capability_mf.domain.validations;

import com.pragma.capability_mf.domain.enums.ErrorMessages;
import com.pragma.capability_mf.domain.exceptions.BusinessException;
import com.pragma.capability_mf.domain.model.Capability;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CapabilityValidations {
    private CapabilityValidations() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateCapability(Capability capability) {
        validateName(capability.name());
        validateDescription(capability.description());
        validateTechnologies(capability.technologies());
    }

    private static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BusinessException(ErrorMessages.CAPABILITY_NEEDS_NAME);
        }
        if (name.length() > 50) {
            throw new BusinessException(ErrorMessages.CAPABILITY_NAME_SIZE);
        }
    }

    private static void validateDescription(String description) {
        if (description.isEmpty()) {
            throw new BusinessException(ErrorMessages.CAPABILITY_NEEDS_DESCRIPTION);
        }
        if (description.length() > 90) {
            throw new BusinessException(ErrorMessages.CAPABILITY_DESCRIPTION_SIZE);
        }
    }

    private static void validateTechnologies(List<Long> technologies) {
        if (technologies.size() < 3) {
            throw new BusinessException(ErrorMessages.CAPABILITY_TECHNOLOGY_MIN_SIZE);
        }
        if (technologies.size() > 20) {
            throw new BusinessException(ErrorMessages.CAPABILITY_TECHNOLOGY_MAX_SIZE);
        }
        Set<Long> set = new HashSet<>(technologies);
        if (set.size() < technologies.size()) {
            throw new BusinessException(ErrorMessages.CAPABILITY_TECHNOLOGY_REPEATED);
        }
    }
}
