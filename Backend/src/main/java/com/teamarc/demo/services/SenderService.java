package com.teamarc.demo.services;

import com.teamarc.demo.entity.*;
import com.teamarc.demo.exceptions.ResourceNotFoundException;
import com.teamarc.demo.repository.ReceiverRepository;
import com.teamarc.demo.repository.SenderConnectionRequestRepository;
import com.teamarc.demo.repository.SenderRepository;
import com.teamarc.demo.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SenderService {
    private final ModelMapper modelMapper;
    private final SenderRepository senderRepository;
    private final SenderConnectionRequestRepository senderConnectionRequestRepository;
    private final SenderConnectionRequestService senderConnectionRequestService;
    private final ReceiverService receiverService;
    private final ReceiverRepository receiverRepository;
    private final TrackingRequestRepository trackingRequestRepository;
    private final JWTService jwtService;


    public void createSender(User user) {
        Sender sender = Sender.builder()
                .user(user)
                .senderId(UUID.randomUUID())
                .build();
        senderRepository.save(sender);
    }

    public void addConnectionRequest(UUID senderId) {
    }

    public List<SenderConnectionRequest> getConnectionRequests() {
        return senderConnectionRequestService.getConnectionRequest(getCurrentSender().getId());
    }

    public Sender getCurrentSender() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return senderRepository.findByUser(user);
    }

    public void acceptConnectionRequest(Long receiverId) {
        Sender sender = getCurrentSender();
        Receiver receiver = receiverService.getReceiverById(receiverId);
        SenderConnectionRequest request = senderConnectionRequestService.getSenderConnectionRequestByReceiverIdAndSenderId(receiverId, sender.getSenderId())
                .orElseThrow(()-> new ResourceNotFoundException("Connection request not found"));
        senderConnectionRequestService.deleteConnectionRequest(request);

         sender.setReceiver(List.of(receiverService.getReceiverById(receiverId)));
         receiver.setSenders(List.of(sender));
         senderRepository.save(sender);
         receiverRepository.save(receiver);
    }

    public void rejectConnectionRequest(Long receiverId) {
        Sender sender = getCurrentSender();
        SenderConnectionRequest request = senderConnectionRequestService.getSenderConnectionRequestByReceiverIdAndSenderId(receiverId, sender.getSenderId())
                .orElseThrow(()-> new ResourceNotFoundException("Connection request not found"));
        senderConnectionRequestService.deleteConnectionRequest(request);
    }

    public List<TrackingRequest> getTrackingRequests() {
        return trackingRequestRepository.findAllBySenderId(getCurrentSender().getId());

    }

    public String acceptTrackingRequest(Long receiverId,long time) {
        Sender sender = getCurrentSender();
        Receiver receiver = receiverService.getReceiverById(receiverId);
        TrackingRequest trackingRequest = trackingRequestRepository.findByReceiverIdAndSenderId(receiverId, sender.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Tracking request not found"));
        trackingRequestRepository.delete(trackingRequest);
        return jwtService.generateTrackingToken(sender.getUser(), receiver.getUser(),time);
    }

    public void rejectTrackingRequest(Long receiverId) {
        TrackingRequest trackingRequest = trackingRequestRepository.findByReceiverIdAndSenderId(receiverId, getCurrentSender().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Tracking request not found"));
        trackingRequestRepository.delete(trackingRequest);
    }

    public void cancelTrackingRequest(Long receiverId) {
        TrackingRequest trackingRequest = trackingRequestRepository.findByReceiverIdAndSenderId(receiverId, getCurrentSender().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Tracking request not found"));
        trackingRequestRepository.delete(trackingRequest);

    }

}
