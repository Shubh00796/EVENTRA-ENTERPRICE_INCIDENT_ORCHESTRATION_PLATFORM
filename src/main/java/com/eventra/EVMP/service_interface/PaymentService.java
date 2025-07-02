package com.eventra.EVMP.service_interface;


import com.eventra.EVMP.dtos.CreatePaymentDTO;
import com.eventra.EVMP.dtos.PaymentResponseDTO;
import com.eventra.EVMP.domain_entities.PaymentMethod;
import com.eventra.EVMP.domain_entities.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentService {

    PaymentResponseDTO createPayment(CreatePaymentDTO createPaymentDTO);

    Optional<PaymentResponseDTO> getPaymentById(Long paymentId);

    Optional<PaymentResponseDTO> getPaymentByReference(String paymentReference);

    Page<PaymentResponseDTO> getPaymentsByRegistrationId(Long registrationId, Pageable pageable);

    Page<PaymentResponseDTO> getPaymentsByStatus(PaymentStatus status, Pageable pageable);

    Page<PaymentResponseDTO> getPaymentsByMethod(PaymentMethod method, Pageable pageable);

    Page<PaymentResponseDTO> getPaymentsByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<PaymentResponseDTO> getPaymentsByStatusAndGateway(PaymentStatus status, String gateway, Pageable pageable);

    Page<PaymentResponseDTO> getPaymentsByAmountRange(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<PaymentResponseDTO> getFailedPaymentsWithReason(Pageable pageable);

    Page<PaymentResponseDTO> getRefundedPayments(Pageable pageable);

    long countPaymentsByStatus(PaymentStatus status);
}
