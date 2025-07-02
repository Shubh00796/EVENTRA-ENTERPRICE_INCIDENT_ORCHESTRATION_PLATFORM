package com.eventra.EVMP.stripeIntigration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentGatewayFactory {

    private final Map<String, PaymentGatewayStrategy> strategies;

    public PaymentGatewayStrategy getStrategy(String gateway) {
        return Optional.ofNullable(strategies.get(gateway.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported payment gateway: " + gateway));
    }
}
