package com.eventra.EVMP.domain_entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pricing_rules", indexes = {
        @Index(name = "idx_event_id", columnList = "eventId"),
        @Index(name = "idx_ticket_type", columnList = "ticketType"),
        @Index(name = "idx_valid_dates", columnList = "validFromDate, validToDate")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pricingRuleId;

    @NotNull
    private Long eventId;

    @NotBlank
    @Size(max = 100)
    private String ruleName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    private BigDecimal basePrice;

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency = "INR";

    @NotNull
    private LocalDateTime validFromDate;

    @NotNull
    private LocalDateTime validToDate;

    @Min(1)
    private Integer minQuantity = 1;

    private Integer maxQuantity;

    @DecimalMin(value = "0.00")
    @DecimalMax(value = "100.00")
    private BigDecimal discountPercentage = BigDecimal.ZERO;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PricingStrategyType pricingStrategy = PricingStrategyType.FIXED;



    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
