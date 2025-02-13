package com.teamarc.demo.controller;

import com.teamarc.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/role/sender")
    public ResponseEntity<Void> setSenderRoleToUser() {
        userService.setSenderRoleToUser();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/role/receiver")
    public ResponseEntity<Void> setReceiverRoleToUser() {
        userService.setReceiverRoleToUser();
        return ResponseEntity.ok().build();
    }
}
