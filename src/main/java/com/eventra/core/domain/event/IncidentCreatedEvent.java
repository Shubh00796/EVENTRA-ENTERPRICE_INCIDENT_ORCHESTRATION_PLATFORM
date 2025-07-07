package com.eventra.core.domain.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentCreatedEvent {
    private String incidentId;
    private String title;
    private String assignedTo;
    private String severity;
    private Instant timestamp;
}

