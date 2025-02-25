package com.pragma.capability_mf.domain.validations;

import com.pragma.capability_mf.domain.enums.ErrorMessages;
import com.pragma.capability_mf.domain.exceptions.BusinessException;
import com.pragma.capability_mf.domain.model.Capability;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.LongStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CapabilityValidationsTest {

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Capability technology = new Capability("id", "", "descripcionPrueba", List.of(1L,2L,3L));
        assertThatThrownBy(() -> CapabilityValidations.validateCapability(technology))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_NEEDS_NAME.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameExceedsMaxLength() {
        Capability technology = new Capability("id", "A".repeat(51), "descripcionPrueba", List.of(1L,2L,3L));

        assertThatThrownBy(() -> CapabilityValidations.validateCapability(technology))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_NAME_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Capability technology = new Capability("id", "nombrePrueba", "", List.of(1L,2L,3L));
        assertThatThrownBy(() -> CapabilityValidations.validateCapability(technology))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_NEEDS_DESCRIPTION.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionExceedsMaxLength() {
        Capability technology = new Capability("id", "nombrePrueba",  "A".repeat(91), List.of(1L,2L,3L));

        assertThatThrownBy(() -> CapabilityValidations.validateCapability(technology))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_DESCRIPTION_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTechnologyListSizeIsLessThanThree() {
        Capability technology = new Capability("id", "nombrePrueba", "descripcionPrueba", List.of(1L,2L));
        assertThatThrownBy(() -> CapabilityValidations.validateCapability(technology))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_TECHNOLOGY_MIN_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTechnologyListHasRepeatedTechnologies() {
        List<Long> numbers = LongStream.rangeClosed(1, 21)
                .boxed()
                .toList();
        Capability technology = new Capability("id", "nombrePrueba", "descripcionPrueba", numbers);
        assertThatThrownBy(() -> CapabilityValidations.validateCapability(technology))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_TECHNOLOGY_MAX_SIZE.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSizeIsLessThanOne() {
        assertThatThrownBy(() -> CapabilityValidations.validateCapabilitySort(0, 1, "", ""))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_PARAM_PAGE_LESS_ZERO.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenPageIsLessThanOne() {
        assertThatThrownBy(() -> CapabilityValidations.validateCapabilitySort(1, 0, "", ""))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_PARAM_SIZE_LESS_ZERO.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenSortByIsInvalid() {
        assertThatThrownBy(() -> CapabilityValidations.validateCapabilitySort(1, 1, "invalid", "asc"))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_SORT_BY_FORMAT.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSortIsInvalid() {
        assertThatThrownBy(() -> CapabilityValidations.validateCapabilitySort(1, 1, "name", "invalid"))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_SORT_FORMAT.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSortAreNullAndSortByNotNull() {
        assertThatThrownBy(() -> CapabilityValidations.validateCapabilitySort(1, 1, "name", null))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_SORT_BY_NO_HAS_SORT.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSortByAreNullAndSortNotNull() {
        assertThatThrownBy(() -> CapabilityValidations.validateCapabilitySort(1, 1, null, "asc"))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorMessages.CAPABILITY_SORT_BY_NO_HAS_SORT.getMessage());
    }
}