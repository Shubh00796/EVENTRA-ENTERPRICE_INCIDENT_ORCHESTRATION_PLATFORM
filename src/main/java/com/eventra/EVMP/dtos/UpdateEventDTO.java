package com.eventra.EVMP.dtos;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateEventDTO {
    private String title;
    private String description;
    private String status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String venueType;
    private String venueDetails;
    private Integer maxCapacity;
    private Boolean waitlistEnabled;
    private LocalDateTime registrationStartDate;
    private LocalDateTime registrationEndDate;
    private String eventTags;
    private String lastModifiedBy;
}
