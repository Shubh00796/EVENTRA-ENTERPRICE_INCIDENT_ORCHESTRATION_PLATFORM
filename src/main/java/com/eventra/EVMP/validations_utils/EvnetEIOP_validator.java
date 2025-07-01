package com.eventra.EVMP.validations_utils;

import com.eventra.EVMP.data_accesslayer.EventRepoService;
import com.eventra.EVMP.dtos.CreateEventDTO;
import com.eventra.EVMP.dtos.UpdateEventDTO;
import com.eventra.EVMP.service_interface.EventService;
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
public class EvnetEIOP_validator {
    private final EventRepoService eventService;
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();


    public void validateCreateRequest(CreateEventDTO dto) {
        Objects.requireNonNull(dto, "event cannot be null");
        validateJsrViolations(dto);
    }

    public void validateUpdateRequest(Long egventId, UpdateEventDTO dto) {
        Objects.requireNonNull(dto, "eventDTO cannot be null");
        validateJsrViolations(dto);
        eventService.findById(egventId); // ensures customer exists
    }

    private <T> void validateJsrViolations(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }


}



