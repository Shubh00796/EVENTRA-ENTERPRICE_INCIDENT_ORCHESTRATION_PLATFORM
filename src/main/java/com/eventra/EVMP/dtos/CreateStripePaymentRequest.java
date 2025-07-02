package com.eventra.EVMP.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStripePaymentRequest {
    @NotNull
    private BigDecimal amount;

    @NotNull
    private String currency;

    @NotNull
    private String paymentMethod; // Stripe token or source ID

    @NotNull
    private Long registrationId;
}
