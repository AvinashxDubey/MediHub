package com.springproject.medihubdata.service;

import com.springproject.medihubdata.dto.LoginRequest;
import com.springproject.medihubdata.dto.LoginResponse;
import com.springproject.medihubdata.model.User;
import com.springproject.medihubdata.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public LoginResponse authenticateStaff(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse("Login successful!!", userOpt.get().getRole());
        }
        return null;
    }
}
