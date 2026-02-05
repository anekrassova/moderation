package org.example.service1.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.service1.entity.RequestCategory;

import java.time.Instant;

@Getter
@Setter
public class ModerationRequestEvent {
    String clientId;
    String eventId;
    RequestCategory category;
    Instant createdAt;
}
