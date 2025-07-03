package com.eventra.EVMP.strategy_utils;

import com.eventra.EVMP.domain_entities.PricingRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("DYNAMIC")
public class DynamicPricingStrategy implements PricingStrategy {
    public BigDecimal calculate(PricingRule rule, int qty) {
        BigDecimal dynamicDiscount = rule.getDiscountPercentage()
                .subtract(BigDecimal.valueOf(Math.min(qty * 2, 15))); // Lose 2% per ticket
        if (dynamicDiscount.compareTo(BigDecimal.ZERO) < 0) {
            dynamicDiscount = BigDecimal.ZERO;
        }
        BigDecimal discount = rule.getBasePrice()
                .multiply(dynamicDiscount)
                .divide(BigDecimal.valueOf(100));
        return rule.getBasePrice().subtract(discount).multiply(BigDecimal.valueOf(qty));
    }
}

