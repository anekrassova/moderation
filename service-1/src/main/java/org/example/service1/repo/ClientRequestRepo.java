package org.example.service1.repo;

import org.example.service1.entity.ClientRequest;
import org.example.service1.entity.RequestCategory;
import org.example.service1.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRequestRepo extends JpaRepository<ClientRequest, Long> {
    Optional<ClientRequest> findByEventId(String eventId);
    boolean existsByEventId(String eventId);
    boolean existsByClientIdAndCategoryAndStatus(
            String clientId,
            RequestCategory category,
            RequestStatus status
    );
}
