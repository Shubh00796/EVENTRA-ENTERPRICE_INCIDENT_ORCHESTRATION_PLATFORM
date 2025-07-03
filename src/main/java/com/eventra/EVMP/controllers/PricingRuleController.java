package com.eventra.EVMP.controllers;


import com.eventra.EVMP.domain_entities.PricingStrategyType;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.dtos.ApiResponse;
import com.eventra.EVMP.dtos.CreatePricingRuleRequestDTO;
import com.eventra.EVMP.dtos.PricingRuleResponseDTO;
import com.eventra.EVMP.dtos.UpdatePricingRuleRequestDTO;
import com.eventra.EVMP.service_interface.PricingRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricing-rules")
@RequiredArgsConstructor
public class PricingRuleController {

    private final PricingRuleService pricingRuleService;

    @PostMapping
    public ResponseEntity<ApiResponse<PricingRuleResponseDTO>> createPricingRule(
            @RequestBody CreatePricingRuleRequestDTO requestDTO) {
        PricingRuleResponseDTO response = pricingRuleService.createPricingRule(requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Pricing rule created successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PricingRuleResponseDTO>> updatePricingRule(
            @PathVariable Long id,
            @RequestBody UpdatePricingRuleRequestDTO requestDTO) {
        PricingRuleResponseDTO response = pricingRuleService.updatePricingRule(id, requestDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Pricing rule updated successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PricingRuleResponseDTO>> getById(@PathVariable Long id) {
        PricingRuleResponseDTO response = pricingRuleService.getPricingRuleById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Pricing rule retrieved successfully", response));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<ApiResponse<Page<PricingRuleResponseDTO>>> getRulesByEventId(
            @PathVariable Long eventId,
            Pageable pageable) {
        Page<PricingRuleResponseDTO> page = pricingRuleService.getPricingRulesByEventId(eventId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Pricing rules fetched by event ID", page));
    }

    @GetMapping("/active/event/{eventId}")
    public ResponseEntity<ApiResponse<Page<PricingRuleResponseDTO>>> getActiveRulesByEventId(
            @PathVariable Long eventId,
            Pageable pageable) {
        Page<PricingRuleResponseDTO> page = pricingRuleService.getActivePricingRulesByEventId(eventId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Active pricing rules fetched successfully", page));
    }

    @GetMapping("/strategy")
    public ResponseEntity<ApiResponse<Page<PricingRuleResponseDTO>>> getByStrategy(
            @RequestParam PricingStrategyType strategy,
            Pageable pageable) {
        Page<PricingRuleResponseDTO> page = pricingRuleService.getRulesByStrategy(strategy, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Rules fetched by strategy", page));
    }

    @GetMapping("/event/{eventId}/discounted")
    public ResponseEntity<ApiResponse<List<PricingRuleResponseDTO>>> getDiscountedRules(@PathVariable Long eventId) {
        List<PricingRuleResponseDTO> response = pricingRuleService.getDiscountedRules(eventId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Discounted rules retrieved", response));
    }

    @GetMapping("/event/{eventId}/ticket-type/{ticketType}")
    public ResponseEntity<ApiResponse<List<PricingRuleResponseDTO>>> getByTicketTypeAndDate(
            @PathVariable Long eventId,
            @PathVariable TicketType ticketType) {
        List<PricingRuleResponseDTO> response = pricingRuleService.getActiveRulesByTicketTypeAndDate(eventId, ticketType);
        return ResponseEntity.ok(new ApiResponse<>(true, "Rules fetched by ticket type and date", response));
    }

    @GetMapping("/event/{eventId}/overlapping")
    public ResponseEntity<ApiResponse<List<PricingRuleResponseDTO>>> getOverlappingRules(
            @PathVariable Long eventId,
            @RequestParam String fromDate,
            @RequestParam String toDate) {
        List<PricingRuleResponseDTO> response = pricingRuleService.getOverlappingRules(eventId, fromDate, toDate);
        return ResponseEntity.ok(new ApiResponse<>(true, "Overlapping rules fetched", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateRule(@PathVariable Long id) {
        pricingRuleService.deactivatePricingRule(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Rule deactivated successfully", null));
    }
}
