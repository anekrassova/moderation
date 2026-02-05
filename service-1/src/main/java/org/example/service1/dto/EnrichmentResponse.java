package org.example.service1.dto;

import lombok.Getter;
import org.example.service1.entity.PriorityLevel;

@Getter
public class EnrichmentResponse {
    private String category;
    private PriorityLevel priority;
}
