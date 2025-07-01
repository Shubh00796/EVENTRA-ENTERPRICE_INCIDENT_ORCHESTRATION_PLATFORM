package com.eventra.EVMP.dtos;


import com.eventra.EVMP.domain_entities.RegistrationStatus;
import com.eventra.EVMP.domain_entities.TicketType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationResponseDTO {

    private Long registrationId;
    private String registrationNumber;
    private Long eventId;
    private String attendeeDetails;
    private TicketType ticketType;
    private RegistrationStatus status;

    private LocalDateTime registrationDate;
    private LocalDateTime confirmationDate;
    private LocalDateTime cancellationDate;

    private String cancellationReason;
    private String specialRequirements;
    private String sourceChannel;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Long version;
}
