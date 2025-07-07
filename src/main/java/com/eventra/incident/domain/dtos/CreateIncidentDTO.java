package com.eventra.incident.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CreateIncidentDTO {

    @NotBlank(message = "Incident ID must not be blank")
    private String incidentId;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "AssignedTo must not be blank")
    private String assignedTo;

    @NotBlank(message = "Severity must not be blank")
    private String severity;
}
