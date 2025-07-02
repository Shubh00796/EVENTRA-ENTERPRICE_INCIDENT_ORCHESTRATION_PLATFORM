package com.eventra.EVMP.dtos;

import com.eventra.EVMP.domain_entities.PaymentMethod;
import com.eventra.EVMP.domain_entities.PaymentStatus;
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
public class PaymentResponseDTO {
    private Long paymentId;
    private String paymentReference;
    private Long registrationId;
    private BigDecimal amount;
    private String currency;
    private PaymentMethod paymentMethod;
    private String paymentGateway;
    private String gatewayTransactionId;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private String gatewayResponse;
    private String failureReason;
    private BigDecimal refundAmount;
    private LocalDateTime refundDate;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
