package com.teamarc.demo.repository;

import com.teamarc.demo.entity.Sender;
import com.teamarc.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {
    Sender findByUser(User user);
}