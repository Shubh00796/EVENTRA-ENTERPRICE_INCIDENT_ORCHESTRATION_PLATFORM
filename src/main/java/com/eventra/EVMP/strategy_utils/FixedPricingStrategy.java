package com.eventra.EVMP.strategy_utils;

import com.eventra.EVMP.domain_entities.PricingRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("FIXED")
public class FixedPricingStrategy implements PricingStrategy {
    public BigDecimal calculate(PricingRule rule, int qty) {
        BigDecimal discount = rule.getBasePrice()
                .multiply(rule.getDiscountPercentage())
                .divide(BigDecimal.valueOf(100));
        BigDecimal discountedPrice = rule.getBasePrice().subtract(discount);
        return discountedPrice.multiply(BigDecimal.valueOf(qty));
    }
}
