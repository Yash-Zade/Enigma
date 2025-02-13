package com.teamarc.demo.services;

import com.teamarc.demo.entity.SenderConnectionRequest;
import com.teamarc.demo.repository.SenderConnectionRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SenderConnectionRequestService {

    private final SenderConnectionRequestRepository senderConnectionRequestRepository;

    public SenderConnectionRequestService(SenderConnectionRequestRepository senderConnectionRequestRepository) {
        this.senderConnectionRequestRepository = senderConnectionRequestRepository;
    }

    public void addConnectionRequest(SenderConnectionRequest request) {
        senderConnectionRequestRepository.save(request);
    }

    public List<SenderConnectionRequest> getConnectionRequest(Long id) {
        return senderConnectionRequestRepository.findAllByReceiverId(id);
    }

    public Optional<SenderConnectionRequest> getSenderConnectionRequestByReceiverIdAndSenderId(Long receiverId, UUID senderId) {
        return senderConnectionRequestRepository.findByReceiverIdAndSenderId(receiverId, senderId);
    }

    public void deleteConnectionRequest(SenderConnectionRequest request) {
        senderConnectionRequestRepository.delete(request);
    }
}
