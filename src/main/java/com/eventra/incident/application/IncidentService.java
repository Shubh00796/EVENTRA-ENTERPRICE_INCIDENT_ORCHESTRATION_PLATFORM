package com.eventra.incident.application;

import com.eventra.EVMP.validations_utils.SystemClock;
import com.eventra.core.domain.event.IncidentCreatedEvent;
import com.eventra.core.infrastructure.kafka.KafkaEventPublisher;
import com.eventra.incident.domain.dtos.CreateIncidentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncidentService {

    private final KafkaEventPublisher eventPublisher;

    public void createIncident(CreateIncidentDTO dto) {
        log.info("🚀 Creating incident: {}", dto);


        IncidentCreatedEvent event = new IncidentCreatedEvent(
                dto.getIncidentId(),
                dto.getTitle(),
                dto.getAssignedTo(),
                dto.getSeverity(),
                Instant.now()
        );

        eventPublisher.publish("incident-created", event);
        log.info("📤 IncidentCreatedEvent published for ID: {}", dto.getIncidentId());
    }

}
