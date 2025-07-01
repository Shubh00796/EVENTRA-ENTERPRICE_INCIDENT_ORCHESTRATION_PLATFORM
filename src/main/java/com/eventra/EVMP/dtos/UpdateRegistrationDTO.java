package com.eventra.EVMP.dtos;


import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.TicketType;
import lombok.Data;

@Data
public class UpdateRegistrationDTO {

    private String attendeeDetails;
    private TicketType ticketType;
    private RegistrationStatus status;
    private String cancellationReason;
    private String specialRequirements;
    private String sourceChannel;
}
