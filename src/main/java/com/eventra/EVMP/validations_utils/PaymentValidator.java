package com.eventra.EVMP.validations_utils;


import com.eventra.EVMP.data_accesslayer.PaymentRepoService;
import com.eventra.EVMP.data_accesslayer.RegistrationRepoService;
import com.eventra.EVMP.domain_entities.RegistrationsEIOP;
import com.eventra.EVMP.dtos.CreatePaymentDTO;
import com.eventra.EVMP.exceptions.DuplicatePaymentException;
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
public class PaymentValidator {

    private final PaymentRepoService paymentRepoService;
    private final RegistrationRepoService repoService;
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validateCreateRequest(CreatePaymentDTO dto) {
        Objects.requireNonNull(dto, "Payment DTO cannot be null");
        validateJsrViolations(dto);
        repoService.findById(dto.getRegistrationId());

        if (paymentRepoService.existsByPaymentReference(dto.getPaymentReference())) {
            throw new DuplicatePaymentException("Payment already processed for reference: " + dto.getPaymentReference());
        }

    }

    private <T> void validateJsrViolations(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }
}
