package com.eventra.EVMP.data_accesslayer;


import com.eventra.EVMP.domain_entities.PaymentEIOP;
import com.eventra.EVMP.domain_entities.PaymentMethod;
import com.eventra.EVMP.domain_entities.PaymentStatus;
import com.eventra.EVMP.exceptions.ResourceNotFoundException;
import com.eventra.EVMP.reposiotries.PaymentEIOPRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentRepoService {

    private final PaymentEIOPRepository paymentRepository;

    public PaymentEIOP findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + id));
    }

    public PaymentEIOP findByReference(String reference) {
        return paymentRepository.findByPaymentReference(reference)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with reference: " + reference));
    }

    public Page<PaymentEIOP> findByRegistrationId(Long registrationId, Pageable pageable) {
        return paymentRepository.findByRegistrationId(registrationId, pageable);
    }

    public Page<PaymentEIOP> findByStatus(PaymentStatus status, Pageable pageable) {
        return paymentRepository.findByStatus(status, pageable);
    }

    public Page<PaymentEIOP> findByMethod(PaymentMethod method, Pageable pageable) {
        return paymentRepository.findByPaymentMethod(method, pageable);
    }

    public Page<PaymentEIOP> findByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return paymentRepository.findByPaymentDateBetween(start, end, pageable);
    }

    public Page<PaymentEIOP> findByStatusAndGateway(PaymentStatus status, String gateway, Pageable pageable) {
        return paymentRepository.findByStatusAndGateway(status, gateway, pageable);
    }

    public Page<PaymentEIOP> findByAmountRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return paymentRepository.findByAmountRange(min, max, pageable);
    }

    public Page<PaymentEIOP> findFailedPaymentsWithReason(Pageable pageable) {
        return paymentRepository.findAllFailedPaymentsWithReason(pageable);
    }

    public Page<PaymentEIOP> findRefundedPayments(Pageable pageable) {
        return paymentRepository.findAllRefundedPayments(pageable);
    }

    public long countByStatus(PaymentStatus status) {
        return paymentRepository.countByStatus(status);
    }

    public boolean existsByPaymentReference(String paymentReference) {
        return paymentRepository.existsByPaymentReference(paymentReference);
    }


    public PaymentEIOP save(PaymentEIOP payment) {
        return paymentRepository.save(payment);
    }


}
