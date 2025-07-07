package com.eventra.incident.infrastructure.web;

import com.eventra.incident.application.IncidentService;
import com.eventra.incident.domain.dtos.CreateIncidentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/incidents/eda")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping
    public ResponseEntity<String> createIncident(@Valid @RequestBody CreateIncidentDTO dto) {
        log.info("Received incident: {}", dto);
        incidentService.createIncident(dto);
        return ResponseEntity.ok("✅ Incident created and published");
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("✅ Incident EDA service is UP");
    }
}
