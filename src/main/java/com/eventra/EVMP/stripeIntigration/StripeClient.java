package com.eventra.EVMP.stripeIntigration;


import com.eventra.EVMP.dtos.CreateStripePaymentRequest;
import com.eventra.EVMP.dtos.StripeChargeResponse;
import com.eventra.EVMP.exceptions.PaymentException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StripeClient {

    public StripeChargeResponse charge(CreateStripePaymentRequest request) {
        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(request.getAmount().multiply(BigDecimal.valueOf(100)).longValue()) // Stripe expects amount in cents
                    .setCurrency(request.getCurrency())
                    .setSource(request.getPaymentMethod())
                    .setDescription("Payment for registration ID: " + request.getRegistrationId())
                    .build();

            Charge charge = Charge.create(params);

            return StripeChargeResponse.builder()
                    .transactionId(charge.getId())
                    .status(charge.getStatus())
                    .build();

        } catch (StripeException e) {
            throw new PaymentException("Stripe payment failed: " + e.getMessage(), e);
        }
    }
}
