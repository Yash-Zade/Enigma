package com.teamarc.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receiver {
    @Id
    private Long id;

    @OneToOne
    private User user;

    @OneToMany
    private List<Sender> senders;
}
