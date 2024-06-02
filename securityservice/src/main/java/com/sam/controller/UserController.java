package com.sam.controller;

import com.sam.dto.LoginRequest;
import com.sam.dto.LoginResponse;
import com.sam.model.User;
import com.sam.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        }

        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Email")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
            } else if (e.getMessage().contains("Username")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already in use");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        }

        Optional<User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (user.isPresent()) {
            // Create a session token
            String sessionToken = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("session", sessionToken);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            LoginResponse loginResponse = new LoginResponse(user.get().getUsername(), user.get().getEmail());
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
