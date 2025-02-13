package com.teamarc.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrackingRequestRepository extends JpaRepository<TrackingRequest, Long> {

  List<TrackingRequest> findAllBySenderId(Long id);

  Optional<TrackingRequest> findByReceiverIdAndSenderId(Long receiverId, Long id);
}