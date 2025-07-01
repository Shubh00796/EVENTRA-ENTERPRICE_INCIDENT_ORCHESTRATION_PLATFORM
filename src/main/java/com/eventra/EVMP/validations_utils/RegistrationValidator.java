package com.eventra.EVMP.validations_utils;

import com.eventra.EVMP.controllers.RegistrationRepoService;
import com.eventra.EVMP.data_accesslayer.EventRepoService;
import com.eventra.EVMP.domain_entities.Events_EIOP;
import com.eventra.EVMP.dtos.CreateRegistrationDTO;
import com.eventra.EVMP.dtos.UpdateRegistrationDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RegistrationValidator {

    private final RegistrationRepoService registrationRepoService;
    private final EventRepoService eventRepoService;
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validateCreateRequest(CreateRegistrationDTO dto) {
        Objects.requireNonNull(dto, "Registration DTO cannot be null");
        validateJsrViolations(dto);
        Events_EIOP events_eiop = eventRepoService.findById(dto.getEventId());
        if (events_eiop == null) {
            throw new IllegalArgumentException("event id can not be null" + events_eiop);
        }

    }

    public void validateUpdateRequest(Long registrationId, UpdateRegistrationDTO dto) {
        Objects.requireNonNull(dto, "Update DTO cannot be null");
        validateJsrViolations(dto);
        registrationRepoService.findById(registrationId); // Ensures registration exists
    }

    private <T> void validateJsrViolations(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }
}
