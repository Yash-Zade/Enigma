package com.teamarc.demo.repository;

import com.teamarc.demo.entity.SenderConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SenderConnectionRequestRepository extends JpaRepository<SenderConnectionRequest, Long> {
    List<SenderConnectionRequest> findAllByReceiverId(Long id);

    Optional<SenderConnectionRequest> findByReceiverIdAndSenderId(Long receiverId, UUID senderId);
}