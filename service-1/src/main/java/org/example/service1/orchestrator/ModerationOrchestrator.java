package org.example.service1.orchestrator;

import lombok.RequiredArgsConstructor;
import org.example.service1.dto.EnrichmentResponse;
import org.example.service1.dto.ModerationAcceptedEvent;
import org.example.service1.dto.ModerationRequestEvent;
import org.example.service1.entity.ClientRequest;
import org.example.service1.http.EnrichmentClient;
import org.example.service1.kafka.ModerationAcceptedEventProducer;
import org.example.service1.service.ClientRequestService;
import org.springframework.stereotype.Component;
import org.example.service1.entity.PriorityLevel;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModerationOrchestrator {
    private final ClientRequestService service;
    private final EnrichmentClient enrichmentClient;
    private final ModerationAcceptedEventProducer producer;

    public void process(ModerationRequestEvent event){
        Optional<ClientRequest> result = service.moderate(event);

        if (result.isEmpty()) {
            return;
        }

        ClientRequest request = result.get();

        EnrichmentResponse enrichment = enrichmentClient.enrich(request.getCategory().name());

        ModerationAcceptedEvent acceptedEvent = buildAcceptedEvent(request, enrichment);

        producer.send(acceptedEvent);
    }

    private ModerationAcceptedEvent buildAcceptedEvent(
            ClientRequest request,
            EnrichmentResponse enrichment
    ) {
        PriorityLevel priority =
                enrichment.getPriority() != null
                        ? enrichment.getPriority()
                        : PriorityLevel.MEDIUM;

        return new ModerationAcceptedEvent(
                request.getClientId(),
                request.getEventId(),
                request.getCategory(),
                priority,
                request.getCreatedAt()
        );
    }
}
