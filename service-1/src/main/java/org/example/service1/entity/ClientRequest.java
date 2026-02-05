package org.example.service1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Entity
@NoArgsConstructor
@Table(
        name = "client_request",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "event_id")
        }
)
public class ClientRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false, updatable = false)
    private String eventId;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public ClientRequest(String eventId, String clientId, RequestCategory category, RequestStatus status, Instant createdAt){
        this.eventId = eventId;
        this.clientId = clientId;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
    }
}
