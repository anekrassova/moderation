package org.example.service1.http;

import org.example.service1.dto.CategoryPriorityRequest;
import org.example.service1.dto.EnrichmentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EnrichmentClient {
    private final WebClient webClient;

    public EnrichmentClient(
            WebClient.Builder webClientBuilder,
            @Value("${service2.base-url}") String baseUrl
    ) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    public EnrichmentResponse enrich(String category) {
        return webClient.post()
                .uri("/enrich/category/priority")
                .bodyValue(new CategoryPriorityRequest(category))
                .retrieve()
                .bodyToMono(EnrichmentResponse.class)
                .block();
    }
}
