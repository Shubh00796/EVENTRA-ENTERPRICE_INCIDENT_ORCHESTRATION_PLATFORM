package com.eventra.EVMP.stripeIntigration;

import com.eventra.EVMP.dtos.CreateStripePaymentRequest;
import com.eventra.EVMP.dtos.StripeChargeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("stripe")
@RequiredArgsConstructor
public class StripePaymentStrategy implements PaymentGatewayStrategy {

    private final StripeClient stripeClient;

    @Override
    public StripeChargeResponse charge(CreateStripePaymentRequest request) {
        return stripeClient.charge(request);
    }
}
