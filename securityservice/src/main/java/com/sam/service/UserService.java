package com.sam.service;


import com.sam.model.User;
import com.sam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(User user) throws IllegalArgumentException {
        // Check if the email or username already exists
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }

        Optional<User> existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserByUsername.isPresent()) {
            throw new IllegalArgumentException("Username is already in use");
        }

        // Encode the password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Save the user
        return userRepository.save(user);
    }


    public Optional<User> authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
}
