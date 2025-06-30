package com.eventra.EVMP.dtos;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponseDTO {
    private Long eventId;
    private String eventCode;
    private String title;
    private String description;
    private String eventType;
    private String status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String timezone;
    private String venueType;
    private String venueDetails;
    private Integer maxCapacity;
    private Integer currentRegistrations;
    private Boolean waitlistEnabled;
    private LocalDateTime registrationStartDate;
    private LocalDateTime registrationEndDate;
    private String eventTags;
    private String organizerDetails;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private Long version;
}
