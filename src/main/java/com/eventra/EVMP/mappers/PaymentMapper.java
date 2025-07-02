package com.eventra.EVMP.mappers;

import com.eventra.EVMP.domain_entities.PaymentEIOP;
import com.eventra.EVMP.dtos.CreatePaymentDTO;
import com.eventra.EVMP.dtos.PaymentResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEIOP toEntity(CreatePaymentDTO request);

    PaymentResponseDTO toResponse(PaymentEIOP payment);
}
