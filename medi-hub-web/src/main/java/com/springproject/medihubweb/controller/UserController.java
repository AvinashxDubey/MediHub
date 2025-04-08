package com.springproject.medihubweb.controller;

import com.springproject.medihubdata.dto.LoginRequest;
import com.springproject.medihubdata.dto.LoginResponse;
import com.springproject.medihubdata.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class UserController {
    private final UserService userService;

    public UserController(UserService staffService) {
        this.userService = staffService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.authenticateStaff(loginRequest);
        if (loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(401).body(new LoginResponse("Invalid username or password", null));
        }
    }
}
