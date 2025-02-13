package com.teamarc.demo.controller;

import com.teamarc.demo.entity.enums.Role;
import com.teamarc.demo.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/role")
    public void setUserRole(Role role) {
        userService.setUserRole(role);
    }
}
