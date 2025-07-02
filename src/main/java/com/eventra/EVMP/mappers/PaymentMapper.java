package com.eventra.EVMP.mappers;

import com.eventra.EVMP.domain_entities.PaymentEIOP;
import com.eventra.EVMP.dtos.CreatePaymentDTO;
import com.eventra.EVMP.dtos.CreateStripePaymentRequest;
import com.eventra.EVMP.dtos.PaymentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentEIOP toEntity(CreatePaymentDTO dto);

    PaymentResponseDTO toResponse(PaymentEIOP entity);

    @Named("toStripeRequest")
    default CreateStripePaymentRequest toStripeRequest(CreatePaymentDTO dto) {
        return CreateStripePaymentRequest.builder()
                .amount(dto.getAmount())
                .currency(dto.getCurrency() != null ? dto.getCurrency() : "INR")
                .paymentMethod(dto.getPaymentToken())    // now explicit
                .registrationId(dto.getRegistrationId())
                .build();
    }
}
