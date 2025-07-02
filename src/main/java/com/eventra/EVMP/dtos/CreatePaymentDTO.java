package com.eventra.EVMP.dtos;

import com.eventra.EVMP.domain_entities.PaymentMethod;
import com.eventra.EVMP.domain_entities.PaymentStatus;
import jakarta.validation.constraints.NotNull;
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
public class CreatePaymentDTO {
    @NotNull
    private String paymentReference;

    @NotNull
    private Long registrationId;

    @NotNull
    private BigDecimal amount;

    private String currency;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private String paymentGateway;

    private String gatewayTransactionId;

    @NotNull
    private PaymentStatus status;

    private LocalDateTime paymentDate;

    private String gatewayResponse;

    private String failureReason;

    private BigDecimal refundAmount;

    private LocalDateTime refundDate;
}
