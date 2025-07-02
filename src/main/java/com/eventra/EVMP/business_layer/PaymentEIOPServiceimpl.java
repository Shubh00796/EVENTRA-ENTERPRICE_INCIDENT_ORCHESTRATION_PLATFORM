package com.eventra.EVMP.business_layer;

import com.eventra.EVMP.data_accesslayer.PaymentRepoService;
import com.eventra.EVMP.domain_entities.PaymentEIOP;
import com.eventra.EVMP.domain_entities.PaymentMethod;
import com.eventra.EVMP.domain_entities.PaymentStatus;
import com.eventra.EVMP.domain_entities.RegistrationsEIOP;
import com.eventra.EVMP.dtos.*;
import com.eventra.EVMP.mappers.PaymentMapper;
import com.eventra.EVMP.service_interface.PaymentService;
import com.eventra.EVMP.stripeIntigration.PaymentGatewayFactory;
import com.eventra.EVMP.stripeIntigration.PaymentGatewayStrategy;
import com.eventra.EVMP.stripeIntigration.StripeClient;
import com.eventra.EVMP.stripeIntigration.StripeStatusMapper;
import com.eventra.EVMP.validations_utils.PaymentValidator;
import com.eventra.EVMP.validations_utils.SystemClock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEIOPServiceimpl implements PaymentService {

    private final PaymentRepoService repoService;
    private final PaymentMapper mapper;
    private final PaymentValidator validator;
    private final StripeClient stripeClient;
    private final SystemClock systemClock;
    private final PaymentGatewayFactory gePaymentGatewayFactory;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PaymentResponseDTO createPayment(CreatePaymentDTO dto) {
        log.info("Creating payment for registration ID: {}", dto.getRegistrationId());
        validator.validateCreateRequest(dto);

        PaymentGatewayStrategy strategy = gePaymentGatewayFactory.getStrategy(dto.getPaymentGateway());

        // 2. Process Stripe payment
        StripeChargeResponse stripeResponse = processStripePayment(dto);

        PaymentEIOP payment = buildPaymentEntity(dto, stripeResponse);
        PaymentEIOP saved = repoService.save(payment);
        log.info("Payment created successfully with ID: {}", saved.getPaymentId());
        return toResponce(saved);
    }


    @Override
    public Optional<PaymentResponseDTO> getPaymentById(Long paymentId) {
        PaymentEIOP paymentEIOP = repoService.findById(paymentId);
        return Optional.ofNullable(toResponce(paymentEIOP));
    }

    @Override
    public Optional<PaymentResponseDTO> getPaymentByReference(String paymentReference) {
        PaymentEIOP reference = repoService.findByReference(paymentReference);
        return Optional.ofNullable(toResponce(reference));
    }

    @Override
    public Page<PaymentResponseDTO> getPaymentsByRegistrationId(Long registrationId, Pageable pageable) {
        return mapToResponsePage(repoService.findByRegistrationId(registrationId, pageable));
    }

    @Override
    public Page<PaymentResponseDTO> getPaymentsByStatus(PaymentStatus status, Pageable pageable) {
        return mapToResponsePage(repoService.findByStatus(status, pageable));
    }

    @Override
    public Page<PaymentResponseDTO> getPaymentsByMethod(PaymentMethod method, Pageable pageable) {
        return mapToResponsePage(repoService.findByMethod(method, pageable));
    }

    @Override
    public Page<PaymentResponseDTO> getPaymentsByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return mapToResponsePage(repoService.findByDateRange(start, end, pageable));
    }

    @Override
    public Page<PaymentResponseDTO> getPaymentsByStatusAndGateway(PaymentStatus status, String gateway, Pageable pageable) {
        return mapToResponsePage(repoService.findByStatusAndGateway(status, gateway, pageable));
    }

    @Override
    public Page<PaymentResponseDTO> getPaymentsByAmountRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return mapToResponsePage(repoService.findByAmountRange(min, max, pageable));
    }

    @Override
    public Page<PaymentResponseDTO> getFailedPaymentsWithReason(Pageable pageable) {
        return mapToResponsePage(repoService.findFailedPaymentsWithReason(pageable));
    }

    @Override
    public Page<PaymentResponseDTO> getRefundedPayments(Pageable pageable) {
        return mapToResponsePage(repoService.findRefundedPayments(pageable));
    }

    @Override
    public long countPaymentsByStatus(PaymentStatus status) {
        return repoService.countByStatus(status);
    }

    /**
     * private helper methods for payments
     */

    private StripeChargeResponse processStripePayment(CreatePaymentDTO dto) {
        CreateStripePaymentRequest stripeRequest = mapper.toStripeRequest(dto);
        return stripeClient.charge(stripeRequest);
    }

    private PaymentEIOP buildPaymentEntity(CreatePaymentDTO dto, StripeChargeResponse stripeResponse) {
        PaymentEIOP entity = mapper.toEntity(dto);
        entity.setGatewayTransactionId(stripeResponse.getTransactionId());
        entity.setPaymentDate(systemClock.now());
        entity.setStatus(StripeStatusMapper.fromStripe(stripeResponse.getStatus()));
        return entity;
    }

    private PaymentResponseDTO toResponce(PaymentEIOP entity) {
        return mapper.toResponse(entity);
    }

    private Page<PaymentResponseDTO> mapToResponsePage(Page<PaymentEIOP> page) {
        return page.map(mapper::toResponse);
    }
}
