package com.eventra.notification.infrastructure.kafka;

import com.eventra.core.domain.event.IncidentCreatedEvent;
import com.eventra.notification.application.NotificationDispatcher;
import com.eventra.notification.domain.enmus.NotificationType;
import com.eventra.notification.domain.model.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IncidentEventConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationDispatcher dispatcher;

    @KafkaListener(topics = "incident-created", groupId = "notification-service")
    public void onMessage(ConsumerRecord<String, String> record) {
        try {
            IncidentCreatedEvent event = objectMapper.readValue(record.value(), IncidentCreatedEvent.class);

            // Build Notification domain object
            Notification notification = Notification.builder()
                    .recipient(event.getAssignedTo()) // assumed to be email
                    .subject("🚨 New Incident: " + event.getTitle())
                    .message("A new incident has been created with severity: " + event.getSeverity())
                    .type(NotificationType.EMAIL)
                    .build();

            dispatcher.dispatch(notification);
        } catch (Exception ex) {
            log.error("❌ Error processing incident event: {}", ex.getMessage(), ex);
        }
    }
}
