package com.teamarc.demo.controller;

import com.teamarc.demo.entity.Receiver;
import com.teamarc.demo.entity.SenderConnectionRequest;
import com.teamarc.demo.entity.User;
import com.teamarc.demo.repository.ReceiverRepository;
import com.teamarc.demo.repository.SenderRepository;
import com.teamarc.demo.services.ReceiverService;
import com.teamarc.demo.services.SenderConnectionRequestService;
import com.teamarc.demo.services.SenderService;
import com.teamarc.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    private final SenderConnectionRequestService senderConnectionRequestService;
    private final ReceiverService receiverService;


    @PostMapping("/connection/request")
    public void sendConnectionRequest(UUID senderId) {
        SenderConnectionRequest request = SenderConnectionRequest.builder()
                .senderId(senderId)
                .receiverId(receiverService.getCurrentReceiver().getId())
                .build();
        senderConnectionRequestService.addConnectionRequest(request);
    }


}
