package com.teamarc.demo.services;

import com.teamarc.demo.entity.Receiver;
import com.teamarc.demo.entity.TrackingRequest;
import com.teamarc.demo.entity.TrackingRequestRepository;
import com.teamarc.demo.entity.User;
import com.teamarc.demo.repository.ReceiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReceiverService {

    private final ReceiverRepository receiverRepository;
    private final TrackingRequestRepository trackingRequestRepository;

    public void createReceiver(User user) {
        Receiver receiver = Receiver.builder()
                .user(user)
                .build();
        receiverRepository.save(receiver);
    }

    public Receiver getReceiverById(Long receiverId) {
        return receiverRepository.findById(receiverId).orElse(null);
    }
    public Receiver getCurrentReceiver() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return receiverRepository.findByUser(user);
    }


    public void addTrackingRequest(Long id) {
        TrackingRequest trackingRequest= TrackingRequest.builder()
                .receiverId(getCurrentReceiver().getId())
                .senderId(id)
                .build();
        trackingRequestRepository.save(trackingRequest);

    }

    public void track(Long id) {

    }
}
