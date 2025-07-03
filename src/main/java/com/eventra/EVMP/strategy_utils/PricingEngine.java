package com.eventra.EVMP.strategy_utils;

import com.eventra.EVMP.domain_entities.PricingRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PricingEngine {

    private final Map<String, PricingStrategy> strategyMap;

    public BigDecimal calculatePrice(PricingRule rule, int quantity) {
        validateQuantity(rule, quantity);

        PricingStrategy strategy = strategyMap.get(rule.getPricingStrategy().name());
        if (strategy == null) {
            throw new UnsupportedOperationException("Strategy not implemented: " + rule.getPricingStrategy());
        }

        return strategy.calculate(rule, quantity);
    }

    private static void validateQuantity(PricingRule rule, int quantity) {
        if (quantity < rule.getMinQuantity() ||
                (rule.getMaxQuantity() != null && quantity > rule.getMaxQuantity())) {
            throw new IllegalArgumentException("Quantity outside allowed range");
        }
    }
}
