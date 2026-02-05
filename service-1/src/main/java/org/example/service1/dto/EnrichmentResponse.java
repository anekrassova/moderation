package org.example.service1.dto;

import lombok.Getter;
import org.apache.kafka.common.protocol.types.Field;
import org.example.service1.entity.PriorityLevel;
import org.example.service1.entity.RequestCategory;

@Getter
public class EnrichmentResponse {
    private String category;
    private PriorityLevel priority;
}
