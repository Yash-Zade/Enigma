package com.teamarc.demo.repository;

import com.teamarc.demo.entity.Receiver;
import com.teamarc.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
    Receiver findByUser(User user);
}