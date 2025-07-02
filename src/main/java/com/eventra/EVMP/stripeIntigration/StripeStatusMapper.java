package com.eventra.EVMP.stripeIntigration;

import com.eventra.EVMP.domain_entities.PaymentStatus;

public class StripeStatusMapper {
    public static PaymentStatus fromStripe(String stripeStatus) {
        return switch (stripeStatus.toLowerCase()) {
            case "succeeded" -> PaymentStatus.COMPLETED;
            case "pending" -> PaymentStatus.PROCESSING;
            case "failed" -> PaymentStatus.FAILED;
            default -> PaymentStatus.PENDING;
        };
    }
}
