package com.eventra.EVMP.dtos;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StripeChargeResponse {
    private String transactionId;
    private String status;
}
