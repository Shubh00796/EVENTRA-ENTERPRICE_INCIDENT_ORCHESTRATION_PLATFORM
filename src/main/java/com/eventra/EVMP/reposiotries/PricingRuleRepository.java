package com.eventra.EVMP.reposiotries;


import com.eventra.EVMP.domain_entities.PricingRule;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.domain_entities.PricingStrategyType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {

    // 🔍 Fetch by event and ticket type for active pricing rules in a given date range
    @Query("SELECT p FROM PricingRule p WHERE p.eventId = :eventId AND p.ticketType = :ticketType " +
            "AND p.isActive = true AND :currentDate BETWEEN p.validFromDate AND p.validToDate")
    List<PricingRule> findActiveRulesByEventAndTicketTypeWithinDate(
            @Param("eventId") Long eventId,
            @Param("ticketType") TicketType ticketType,
            @Param("currentDate") LocalDateTime currentDate);

    Page<PricingRule> findByEventId(Long eventId, Pageable pageable);

    Page<PricingRule> findByEventIdAndIsActiveTrue(Long eventId, Pageable pageable);

    @Query("SELECT p FROM PricingRule p WHERE p.eventId = :eventId AND " +
            "((:fromDate BETWEEN p.validFromDate AND p.validToDate) OR " +
            "(:toDate BETWEEN p.validFromDate AND p.validToDate)) AND p.isActive = true")
    List<PricingRule> findOverlappingRules(
            @Param("eventId") Long eventId,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate);

    Page<PricingRule> findByPricingStrategy(PricingStrategyType pricingStrategy, Pageable pageable);

    List<PricingRule> findByDiscountPercentageGreaterThan(BigDecimal discountThreshold);

    Optional<PricingRule> findTopByOrderByCreatedDateDesc();
}
