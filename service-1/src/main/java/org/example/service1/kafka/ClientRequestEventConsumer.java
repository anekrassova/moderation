package org.example.service1.kafka;


import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service1.dto.ModerationRequestEvent;
import org.example.service1.orchestrator.ModerationOrchestrator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientRequestEventConsumer {
    private final ModerationOrchestrator orchestrator;
    private final MeterRegistry meterRegistry;

    @KafkaListener(topics = "topic-1")
    public void consume(
            ModerationRequestEvent event,
            Acknowledgment ack
    ) {
        long start = System.currentTimeMillis();
        //log.info("Received ModerationRequestEvent: {}", event);
        try {
            orchestrator.process(event);

            meterRegistry.counter("messages_processed_total").increment();
        } catch (Exception e) {
            meterRegistry.counter("messages_failed_total").increment();

            log.error("Failed to process event {}", event.getEventId(), e);
        } finally {
            ack.acknowledge();

            long duration = System.currentTimeMillis() - start;

            meterRegistry.timer("message_processing_time")
                    .record(duration, TimeUnit.MILLISECONDS);
        }
    }
}
