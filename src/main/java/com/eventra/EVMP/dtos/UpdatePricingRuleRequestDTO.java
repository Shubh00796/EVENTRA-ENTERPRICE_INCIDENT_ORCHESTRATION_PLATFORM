package com.eventra.EVMP.dtos;

import com.eventra.EVMP.domain_entities.PricingStrategyType;
import com.eventra.EVMP.domain_entities.TicketType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePricingRuleRequestDTO {

    @NotBlank
    @Size(max = 100)
    private String ruleName;

    @NotNull
    private TicketType ticketType;

    @NotNull
    private BigDecimal basePrice;

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    private LocalDateTime validFromDate;

    @NotNull
    private LocalDateTime validToDate;

    @Min(1)
    private Integer minQuantity;

    private Integer maxQuantity;

    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal discountPercentage;

    @NotNull
    private PricingStrategyType pricingStrategy;

    private String strategyConfig;

    private Boolean isActive;
}
