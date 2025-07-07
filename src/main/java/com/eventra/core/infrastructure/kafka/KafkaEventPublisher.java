package com.eventra.core.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public <T> void publish(String topic, T event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(new ProducerRecord<>(topic, message));
            log.info("✅ Published event to topic '{}': {}", topic, message);
        } catch (Exception e) {
            log.error("❌ Failed to publish event to topic '{}': {}", topic, e.getMessage(), e);
        }
    }
}
