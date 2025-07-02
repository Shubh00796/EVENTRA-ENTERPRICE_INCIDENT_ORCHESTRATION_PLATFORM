package com.eventra.EVMP.domain_entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments_for_eiop", indexes = {
        @Index(name = "idx_registration_id", columnList = "registrationId"),
        @Index(name = "idx_payment_reference", columnList = "paymentReference"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_payment_date", columnList = "paymentDate")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEIOP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @NotNull
    @Column(unique = true, length = 50)
    private String paymentReference;

    @NotNull
    private Long registrationId;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(length = 3)
    private String currency = "INR";

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull
    @Column(length = 50)
    private String paymentGateway;

    @Column(length = 100)
    private String gatewayTransactionId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paymentDate;

    @Lob
    private String gatewayResponse;

    @Lob
    private String failureReason;

    @Column(precision = 10, scale = 2)
    private BigDecimal refundAmount = BigDecimal.ZERO;

    private LocalDateTime refundDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;
}
