package com.eventra.EVMP.controllers;

import com.eventra.EVMP.domain_entities.PaymentMethod;
import com.eventra.EVMP.domain_entities.PaymentStatus;
import com.eventra.EVMP.dtos.*;
import com.eventra.EVMP.service_interface.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> createPayment(@RequestBody CreatePaymentDTO dto) {
        PaymentResponseDTO response = paymentService.createPayment(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment created successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(res -> ResponseEntity.ok(new ApiResponse<>(true, "Payment retrieved", res)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reference/{paymentReference}")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> getByReference(@PathVariable String paymentReference) {
        return paymentService.getPaymentByReference(paymentReference)
                .map(res -> ResponseEntity.ok(new ApiResponse<>(true, "Payment retrieved by reference", res)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/registration")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getByRegistrationId(
            @RequestParam Long registrationId, Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getPaymentsByRegistrationId(registrationId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments fetched by registration", page));
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getByStatus(
            @RequestParam PaymentStatus status, Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getPaymentsByStatus(status, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments by status", page));
    }

    @GetMapping("/method")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getByMethod(
            @RequestParam PaymentMethod method, Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getPaymentsByMethod(method, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments by method", page));
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end,
            Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getPaymentsByDateRange(start, end, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments by date range", page));
    }

    @GetMapping("/status-gateway")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getByStatusAndGateway(
            @RequestParam PaymentStatus status,
            @RequestParam String gateway,
            Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getPaymentsByStatusAndGateway(status, gateway, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments by status and gateway", page));
    }

    @GetMapping("/amount-range")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getByAmountRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getPaymentsByAmountRange(min, max, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payments by amount range", page));
    }

    @GetMapping("/failed")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getFailedPayments(Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getFailedPaymentsWithReason(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Failed payments fetched", page));
    }

    @GetMapping("/refunded")
    public ResponseEntity<ApiResponse<Page<PaymentResponseDTO>>> getRefundedPayments(Pageable pageable) {
        Page<PaymentResponseDTO> page = paymentService.getRefundedPayments(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Refunded payments fetched", page));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> countByStatus(@RequestParam PaymentStatus status) {
        long count = paymentService.countPaymentsByStatus(status);
        return ResponseEntity.ok(new ApiResponse<>(true, "Count retrieved", count));
    }
}
