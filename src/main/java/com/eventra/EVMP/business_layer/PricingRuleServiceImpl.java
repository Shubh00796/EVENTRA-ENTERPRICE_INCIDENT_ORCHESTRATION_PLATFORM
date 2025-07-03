package com.eventra.EVMP.business_layer;

import com.eventra.EVMP.data_accesslayer.PricingRuleRepoService;
import com.eventra.EVMP.domain_entities.PricingRule;
import com.eventra.EVMP.domain_entities.PricingStrategyType;
import com.eventra.EVMP.domain_entities.TicketType;
import com.eventra.EVMP.dtos.CreatePricingRuleRequestDTO;
import com.eventra.EVMP.dtos.PricingRuleResponseDTO;
import com.eventra.EVMP.dtos.UpdatePricingRuleRequestDTO;
import com.eventra.EVMP.mappers.PricingRuleMapper;
import com.eventra.EVMP.service_interface.PricingRuleService;
import com.eventra.EVMP.strategy_utils.PricingEngine;
import com.eventra.EVMP.validations_utils.PricingRuleValidator;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepoService repoService;
    private final PricingRuleMapper mapper;
    private final PricingRuleValidator validator;
    private final SystemClock systemClock;
    private final PricingEngine pricingEngine;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PricingRuleResponseDTO createPricingRule(CreatePricingRuleRequestDTO requestDTO) {
        validator.validateCreateRequest(requestDTO);
        PricingRule pricingRule = mapper.toEntity(requestDTO);
        pricingRule.setCreatedDate(systemClock.now());
        PricingRule savedRule = repoService.save(pricingRule);

        BigDecimal testPrice = pricingEngine.calculatePrice(savedRule, savedRule.getMinQuantity());
        log.info("Rule [{}] saved using strategy [{}]. Test price for minQuantity={} is ₹{}",
                savedRule.getRuleName(), savedRule.getPricingStrategy(),
                savedRule.getMinQuantity(), testPrice);

        return mapper.toDto(savedRule);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PricingRuleResponseDTO updatePricingRule(Long pricingRuleId, UpdatePricingRuleRequestDTO requestDTO) {
        validator.validateUpdateRequest(pricingRuleId, requestDTO);
        PricingRule existingRule = repoService.findById(pricingRuleId);
        mapper.updateEntity(existingRule, requestDTO);
        PricingRule updatedRule = repoService.update(existingRule);
        return mapper.toDto(updatedRule);
    }

    @Override
    public PricingRuleResponseDTO getPricingRuleById(Long pricingRuleId) {
        PricingRule rule = repoService.findById(pricingRuleId);
        return mapper.toDto(rule);
    }

    @Override
    public Page<PricingRuleResponseDTO> getPricingRulesByEventId(Long eventId, Pageable pageable) {
        return toDtoPage(repoService.findByEventId(eventId, pageable));
    }

    @Override
    public Page<PricingRuleResponseDTO> getActivePricingRulesByEventId(Long eventId, Pageable pageable) {
        return toDtoPage(repoService.findActiveByEventId(eventId, pageable));
    }

    @Override
    public Page<PricingRuleResponseDTO> getPaginatedRulesByEventId(Long eventId, Pageable pageable) {
        return toDtoPage(repoService.findByEventId(eventId, pageable));
    }

    @Override
    public Page<PricingRuleResponseDTO> getPaginatedActiveRulesByEventId(Long eventId, Pageable pageable) {
        return toDtoPage(repoService.findActiveByEventId(eventId, pageable));
    }

    @Override
    public List<PricingRuleResponseDTO> getActiveRulesByTicketTypeAndDate(Long eventId, TicketType ticketType) {
        return toDtoList(repoService.findActiveRulesByEventAndTicketTypeWithinDate(eventId, ticketType, systemClock.now()));
    }

    @Override
    public List<PricingRuleResponseDTO> getOverlappingRules(Long eventId, String fromDate, String toDate) {
        return toDtoList(repoService.findOverlappingRules(eventId, parseDate(fromDate), parseDate(toDate)));
    }

    @Override
    public Page<PricingRuleResponseDTO> getRulesByStrategy(PricingStrategyType strategy, Pageable pageable) {
        return toDtoPage(repoService.findByPricingStrategy(strategy, pageable));
    }

    @Override
    public List<PricingRuleResponseDTO> getDiscountedRules(Long eventId) {
        return toDtoList(repoService.findByDiscountThreshold(BigDecimal.ZERO)
                .stream().filter(rule -> rule.getEventId().equals(eventId)).toList());
    }

    @Override
    public List<PricingRuleResponseDTO> getLatestPricingRules(int limit) {
        return List.of(toDto(repoService.findLatestRule()));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deactivatePricingRule(Long pricingRuleId) {
        PricingRule rule = repoService.findById(pricingRuleId);
        rule.setIsActive(false);
        repoService.update(rule);
        log.info("Deactivated Pricing Rule with ID: {}", pricingRuleId);
    }

    private PricingRuleResponseDTO toDto(PricingRule rule) {
        return mapper.toDto(rule);
    }

    private List<PricingRuleResponseDTO> toDtoList(List<PricingRule> rules) {
        return rules.stream().map(mapper::toDto).toList();
    }

    private Page<PricingRuleResponseDTO> toDtoPage(Page<PricingRule> page) {
        return page.map(mapper::toDto);
    }

    private LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr);
    }


}
