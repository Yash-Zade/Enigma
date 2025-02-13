package com.teamarc.demo.services;

import com.teamarc.demo.entity.Sender;
import com.teamarc.demo.entity.User;
import com.teamarc.demo.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SenderService {
    private final ModelMapper modelMapper;
    private final SenderRepository senderRepository;

    public void createSender(User user) {
        Sender sender = Sender.builder()
                .user(user)
                .build();
        senderRepository.save(sender);
    }
}
