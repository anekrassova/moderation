package org.example.service1.kafka;

import lombok.RequiredArgsConstructor;
import org.example.service1.dto.ModerationAcceptedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModerationAcceptedEventProducer {
    private final KafkaTemplate<String, ModerationAcceptedEvent> kafkaTemplate;

    public void send(ModerationAcceptedEvent event) {
        kafkaTemplate.send(
                "topic-2",
                event.getEventId(),
                event
        );
    }
}
