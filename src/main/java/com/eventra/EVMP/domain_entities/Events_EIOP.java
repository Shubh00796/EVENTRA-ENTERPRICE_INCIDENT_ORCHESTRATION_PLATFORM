package com.eventra.EVMP.domain_entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events", indexes = {
        @Index(name = "idx_event_type", columnList = "event_type"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_start_date", columnList = "start_date_time"),
        @Index(name = "idx_event_code", columnList = "eventCode")
})
public class Events_EIOP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @NotNull
    @Column(unique = true)
    private String eventCode;

    @NotNull
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private EventStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    @NotNull
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(name = "venue_type")
    @NotNull
    private VenueType venueType;


    @NotNull
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @NotNull
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @NotNull
    private String timezone;


    private String venueDetails;

    @NotNull
    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "current_registrations")
    private Integer currentRegistrations;

    @Column(name = "waitlist_enabled")
    private Boolean waitlistEnabled;

    @NotNull
    @Column(name = "registration_start_date")
    private LocalDateTime registrationStartDate;

    @NotNull
    @Column(name = "registration_end_date")
    private LocalDateTime registrationEndDate;

    @Column(name = "event_tags")
    private String eventTags;

    @NotNull
    @Column(name = "organizer_details")
    private String organizerDetails;

    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    private Long version;
}
