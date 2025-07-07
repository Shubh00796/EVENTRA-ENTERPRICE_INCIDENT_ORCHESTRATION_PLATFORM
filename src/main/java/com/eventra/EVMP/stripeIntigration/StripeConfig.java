package com.eventra.EVMP.stripeIntigration;



import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StripeConfig {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public void logStripeKeys() {
        System.out.println("✅ Stripe Secret Key loaded: " + secretKey);
    }
}

