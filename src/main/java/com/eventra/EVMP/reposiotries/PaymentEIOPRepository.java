package com.eventra.EVMP.reposiotries;

import com.eventra.EVMP.domain_entities.PaymentEIOP;
import com.eventra.EVMP.domain_entities.PaymentMethod;
import com.eventra.EVMP.domain_entities.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentEIOPRepository extends JpaRepository<PaymentEIOP, Long> {

    Optional<PaymentEIOP> findByPaymentReference(String paymentReference);

    Page<PaymentEIOP> findByRegistrationId(Long registrationId, Pageable pageable);

    Page<PaymentEIOP> findByStatus(PaymentStatus status, Pageable pageable);

    Page<PaymentEIOP> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<PaymentEIOP> findByPaymentMethod(PaymentMethod paymentMethod, Pageable pageable);

    @Query("SELECT p FROM PaymentEIOP p WHERE p.status = :status AND p.paymentGateway = :gateway")
    Page<PaymentEIOP> findByStatusAndGateway(@Param("status") PaymentStatus status, @Param("gateway") String gateway, Pageable pageable);

    @Query("SELECT p FROM PaymentEIOP p WHERE p.amount >= :minAmount AND p.amount <= :maxAmount")
    Page<PaymentEIOP> findByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount, Pageable pageable);

    @Query("SELECT p FROM PaymentEIOP p WHERE p.status = 'FAILED' AND p.failureReason IS NOT NULL")
    Page<PaymentEIOP> findAllFailedPaymentsWithReason(Pageable pageable);

    @Query("SELECT p FROM PaymentEIOP p WHERE p.status = 'REFUNDED' OR p.status = 'PARTIALLY_REFUNDED'")
    Page<PaymentEIOP> findAllRefundedPayments(Pageable pageable);

    @Query("SELECT COUNT(p) FROM PaymentEIOP p WHERE p.status = :status")
    long countByStatus(@Param("status") PaymentStatus status);

    boolean existsByPaymentReference(String paymentReference);
}
