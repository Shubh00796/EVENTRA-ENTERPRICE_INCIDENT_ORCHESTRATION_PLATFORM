package com.eventra.EVMP.stripeIntigration;

import com.eventra.EVMP.dtos.CreateStripePaymentRequest;
import com.eventra.EVMP.dtos.StripeChargeResponse;

public interface PaymentGatewayStrategy {
    StripeChargeResponse charge(CreateStripePaymentRequest request);
}
