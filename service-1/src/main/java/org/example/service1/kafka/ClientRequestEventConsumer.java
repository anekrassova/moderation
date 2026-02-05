package org.example.service1.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service1.dto.ModerationRequestEvent;
import org.example.service1.orchestrator.ModerationOrchestrator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientRequestEventConsumer {
    private final ModerationOrchestrator orchestrator;

    @KafkaListener(topics = "topic-1")
    public void consume(
            ModerationRequestEvent event,
            Acknowledgment ack
    ) {
        log.info("Received ModerationRequestEvent: {}", event);
        try {
            orchestrator.process(event);

            ack.acknowledge();
        } catch (Exception e) {
            log.error("Failed to process event {}", event.getEventId(), e);
        }
    }
}
