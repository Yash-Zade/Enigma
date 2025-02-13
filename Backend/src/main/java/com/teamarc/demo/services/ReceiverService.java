package com.teamarc.demo.services;

import com.teamarc.demo.entity.Receiver;
import com.teamarc.demo.entity.User;
import com.teamarc.demo.repository.ReceiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiverService {

    private final ReceiverRepository receiverRepository;

    public void createReceiver(User user) {
        Receiver receiver = Receiver.builder()
                .user(user)
                .build();
        receiverRepository.save(receiver);
    }
}
