package com.eventra.EVMP.dtos;

import com.eventra.EVMP.domain_entities.PricingStrategyType;
import com.eventra.EVMP.domain_entities.TicketType;
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
public class PricingRuleResponseDTO {

    private Long pricingRuleId;
    private Long eventId;
    private String ruleName;
    private TicketType ticketType;
    private BigDecimal basePrice;
    private String currency;
    private LocalDateTime validFromDate;
    private LocalDateTime validToDate;
    private Integer minQuantity;
    private Integer maxQuantity;
    private BigDecimal discountPercentage;
    private PricingStrategyType pricingStrategy;
    private String strategyConfig;
    private Boolean isActive;
    private LocalDateTime createdDate;
}
