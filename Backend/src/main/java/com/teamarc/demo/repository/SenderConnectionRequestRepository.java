package com.teamarc.demo.repository;

import com.teamarc.demo.entity.SenderConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SenderConnectionRequestRepository extends JpaRepository<SenderConnectionRequest, Long> {
    List<SenderConnectionRequest> findAllByReceiverId(Long id);

    Optional<SenderConnectionRequest> findByReceiverIdAndSenderId(Long receiverId, UUID senderId);
}