package com.eventra.EVMP.strategy_utils;

import com.eventra.EVMP.domain_entities.PricingRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("TIERED")
public class TiredPricingStrategy implements PricingStrategy {
    public BigDecimal calculate(PricingRule rule, int qty) {
        BigDecimal tierDiscount;
        tierDiscount = validateQuantity(qty);

        BigDecimal discount = rule.getBasePrice()
                .multiply(tierDiscount)
                .divide(BigDecimal.valueOf(100));
        return rule.getBasePrice().subtract(discount).multiply(BigDecimal.valueOf(qty));
    }

    private static BigDecimal validateQuantity(int qty) {
        BigDecimal tierDiscount;
        if (qty >= 10) {
            tierDiscount = BigDecimal.valueOf(20);
        } else if (qty >= 5) {
            tierDiscount = BigDecimal.valueOf(10);
        } else {
            tierDiscount = BigDecimal.ZERO;
        }
        return tierDiscount;
    }
}
