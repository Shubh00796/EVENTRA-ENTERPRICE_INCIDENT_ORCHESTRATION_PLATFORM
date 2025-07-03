package com.eventra.EVMP.validations_utils;

import com.eventra.EVMP.data_accesslayer.EventRepoService;
import com.eventra.EVMP.data_accesslayer.PricingRuleRepoService;
import com.eventra.EVMP.data_accesslayer.RegistrationRepoService;
import com.eventra.EVMP.domain_entities.Events_EIOP;
import com.eventra.EVMP.domain_entities.RegistrationsEIOP;
import com.eventra.EVMP.dtos.CreatePricingRuleRequestDTO;
import com.eventra.EVMP.dtos.UpdatePricingRuleRequestDTO;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PricingRuleValidator {

    private final RegistrationRepoService repoService;
    private final PricingRuleRepoService pricingRuleRepoService;

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validateCreateRequest(CreatePricingRuleRequestDTO dto) {
        Objects.requireNonNull(dto, "Pricing Rule DTO cannot be null");
        validateJsrViolations(dto);

        RegistrationsEIOP service = repoService.findById(dto.getEventId());
        if (service == null) {
            throw new IllegalArgumentException("Invalid event ID: " + dto.getEventId());
        }
    }

    public void validateUpdateRequest(Long pricingRuleId, UpdatePricingRuleRequestDTO dto) {
        Objects.requireNonNull(dto, "Update DTO cannot be null");
        validateJsrViolations(dto);

        pricingRuleRepoService.findById(pricingRuleId); // Confirms rule exists
    }

    private <T> void validateJsrViolations(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + violations);
        }
    }
}
