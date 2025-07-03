package com.eventra.EVMP.strategy_utils;

import com.eventra.EVMP.domain_entities.PricingRule;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculate(PricingRule rule, int quantity);
}
