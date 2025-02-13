package com.teamarc.demo.services;

import com.teamarc.demo.entity.Sender;
import com.teamarc.demo.entity.SenderConnectionRequest;
import com.teamarc.demo.entity.User;
import com.teamarc.demo.exceptions.ResourceNotFoundException;
import com.teamarc.demo.repository.SenderConnectionRequestRepository;
import com.teamarc.demo.repository.SenderRepository;
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

    public void createSender(User user) {
        Sender sender = Sender.builder()
                .user(user)
                .build();
        senderRepository.save(sender);
    }

    public void addConnectionRequest(UUID senderId) {
    }

    public List<SenderConnectionRequest> getConnectionRequests() {
        return senderConnectionRequestService.getConnectionRequest(getCurrentSender().getId());
    }

    private Sender getCurrentSender() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return senderRepository.findByUser(user);
    }

    public void acceptConnectionRequest(Long receiverId) {
        Sender sender = getCurrentSender();
        SenderConnectionRequest request = senderConnectionRequestService.getSenderConnectionRequestByReceiverIdAndSenderId(receiverId, sender.getSenderId())
                .orElseThrow(()-> new ResourceNotFoundException("Connection request not found"));
        senderConnectionRequestService.deleteConnectionRequest(request);

         sender.setReceiver(List.of(receiverService.getReceiverById(receiverId)));

    }
}
