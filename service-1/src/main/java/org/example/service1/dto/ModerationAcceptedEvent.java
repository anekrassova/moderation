package org.example.service1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.service1.entity.PriorityLevel;
import org.example.service1.entity.RequestCategory;

import java.time.Instant;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModerationAcceptedEvent {
    private String clientId;
    private String eventId;
    private RequestCategory category;
    private PriorityLevel priority;
    private Instant createdAt;
}
