package org.example.service1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service1.domain.WorkingHoursRule;
import org.example.service1.dto.ModerationRequestEvent;
import org.example.service1.entity.ClientRequest;
import org.example.service1.entity.RequestStatus;
import org.example.service1.repo.ClientRequestRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientRequestService {
    private final ClientRequestRepo clientRequestRepo;

    @Transactional
    public Optional<ClientRequest> moderate(ModerationRequestEvent moderationRequest) {
        if (clientRequestRepo.existsByEventId(moderationRequest.getEventId())) {
            log.info("Moderation request already moderated");
            return Optional.empty();
        }

        if (clientRequestRepo.existsByClientIdAndCategoryAndStatus(moderationRequest.getClientId(), moderationRequest.getCategory(), RequestStatus.ACTIVE)){
            log.info("Client has ACTIVE moderation request in provided category");
            return Optional.empty();
        }

        if(!WorkingHoursRule.isAllowed(moderationRequest.getCategory(), moderationRequest.getCreatedAt())){
            log.info("Moderation request is not allowed for this category in non-working hours");
            return Optional.empty();
        }

        ClientRequest clientRequest = new ClientRequest(
                moderationRequest.getEventId(),
                moderationRequest.getClientId(),
                moderationRequest.getCategory(),
                RequestStatus.ACTIVE,
                moderationRequest.getCreatedAt()
        );

        try {
            clientRequestRepo.save(clientRequest);
            log.info("Moderation request moderated (saved to DB)");
        } catch (DataIntegrityViolationException e){
            return Optional.empty();
        }

        return Optional.of(clientRequest);
    }
}
