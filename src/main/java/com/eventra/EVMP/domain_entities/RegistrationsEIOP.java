package com.eventra.EVMP.domain_entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "registrations", indexes = {
        @Index(name = "idx_event_id", columnList = "event_id"),
        @Index(name = "idx_registration_number", columnList = "registration_number"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_registration_date", columnList = "registration_date")
})
public class RegistrationsEIOP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long registrationId;

    @Column(name = "registration_number")
    private String registrationNumber;

    @NotNull
    @Column(name = "event_id")
    private Long eventId;

    @NotNull
    @Column(name = "attendee_details", columnDefinition = "TEXT")
    private String attendeeDetails;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", length = 20)
    private TicketType ticketType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private RegistrationStatus status;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @Column(name = "cancellation_date")
    private LocalDateTime cancellationDate;

    @Column(name = "cancellation_reason", columnDefinition = "TEXT")
    private String cancellationReason;

    @Column(name = "special_requirements", columnDefinition = "TEXT")
    private String specialRequirements;

    @Column(name = "source_channel", length = 50)
    private String sourceChannel;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "version")
    private Long version;
}
