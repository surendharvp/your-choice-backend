package com.backEnd.serviceMarketplace.service;

import org.springframework.stereotype.Service;

import com.backEnd.serviceMarketplace.model.User;
import com.backEnd.serviceMarketplace.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Add password hashing and validation logic here
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
