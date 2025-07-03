package com.eventra.EVMP.data_accesslayer;

import com.eventra.EVMP.domain_entities.PricingRule;
import com.eventra.EVMP.domain_entities.PricingStrategyType;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.exceptions.ResourceNotFoundException;
import com.eventra.EVMP.reposiotries.PricingRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingRuleRepoService {

    private final PricingRuleRepository pricingRuleRepository;

    public PricingRule findById(Long id) {
        return pricingRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pricing Rule not found with ID: " + id));
    }

    public Page<PricingRule> findByEventId(Long eventId, Pageable pageable) {
        return pricingRuleRepository.findByEventId(eventId, pageable);
    }

    public Page<PricingRule> findActiveByEventId(Long eventId, Pageable pageable) {
        return pricingRuleRepository.findByEventIdAndIsActiveTrue(eventId, pageable);
    }

    public List<PricingRule> findActiveRulesByEventAndTicketTypeWithinDate(Long eventId, TicketType ticketType, LocalDateTime currentDate) {
        return pricingRuleRepository.findActiveRulesByEventAndTicketTypeWithinDate(eventId, ticketType, currentDate);
    }

    public List<PricingRule> findOverlappingRules(Long eventId, LocalDateTime fromDate, LocalDateTime toDate) {
        return pricingRuleRepository.findOverlappingRules(eventId, fromDate, toDate);
    }

    public Page<PricingRule> findByPricingStrategy(PricingStrategyType strategy, Pageable pageable) {
        return pricingRuleRepository.findByPricingStrategy(strategy, pageable);
    }

    public List<PricingRule> findByDiscountThreshold(BigDecimal threshold) {
        return pricingRuleRepository.findByDiscountPercentageGreaterThan(threshold);
    }

    public PricingRule findLatestRule() {
        return pricingRuleRepository.findTopByOrderByCreatedDateDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No pricing rules found"));
    }

    public PricingRule save(PricingRule rule) {
        return pricingRuleRepository.save(rule);
    }

    public PricingRule update(PricingRule rule) {
        return pricingRuleRepository.save(rule);
    }
}
