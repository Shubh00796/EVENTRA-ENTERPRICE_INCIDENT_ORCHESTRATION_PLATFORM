package com.eventra.EVMP.dtos;


import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.TicketType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRegistrationDTO {

    // This will be auto-generated in the service layer
    private String registrationNumber;

    @NotNull
    private Long eventId;

    @NotNull
    private String attendeeDetails;

    @NotNull
    private TicketType ticketType;

    @NotNull
    private RegistrationStatus status;

    private String cancellationReason;
    private String specialRequirements;
    private String sourceChannel;
}
