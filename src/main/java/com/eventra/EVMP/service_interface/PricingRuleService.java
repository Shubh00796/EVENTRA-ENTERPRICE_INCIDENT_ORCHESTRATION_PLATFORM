package com.eventra.EVMP.service_interface;


import com.eventra.EVMP.dtos.*;
import com.eventra.EVMP.domain_entities.PricingStrategyType;
import com.eventra.EVMP.domain_entities.TicketType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PricingRuleService {

    PricingRuleResponseDTO createPricingRule(CreatePricingRuleRequestDTO requestDTO);

    PricingRuleResponseDTO updatePricingRule(Long pricingRuleId, UpdatePricingRuleRequestDTO requestDTO);

    PricingRuleResponseDTO getPricingRuleById(Long pricingRuleId);

    Page<PricingRuleResponseDTO> getPricingRulesByEventId(Long eventId, Pageable pageable);

    Page<PricingRuleResponseDTO> getActivePricingRulesByEventId(Long eventId, Pageable pageable);

    Page<PricingRuleResponseDTO> getPaginatedRulesByEventId(Long eventId, Pageable pageable);

    Page<PricingRuleResponseDTO> getPaginatedActiveRulesByEventId(Long eventId, Pageable pageable);

    List<PricingRuleResponseDTO> getActiveRulesByTicketTypeAndDate(Long eventId, TicketType ticketType);

    List<PricingRuleResponseDTO> getOverlappingRules(Long eventId, String fromDate, String toDate); // optionally LocalDateTime

    Page<PricingRuleResponseDTO> getRulesByStrategy(PricingStrategyType strategy, Pageable pageable);

    List<PricingRuleResponseDTO> getDiscountedRules(Long eventId);

    List<PricingRuleResponseDTO> getLatestPricingRules(int limit);

    void deactivatePricingRule(Long pricingRuleId);
}
