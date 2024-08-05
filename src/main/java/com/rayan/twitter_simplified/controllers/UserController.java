package com.rayan.twitter_simplified.controllers;

import com.rayan.twitter_simplified.domain.user.User;
import com.rayan.twitter_simplified.domain.user.dto.RegisterUserRequestPayload;
import com.rayan.twitter_simplified.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling user-related requests.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register a new user.
     *
     * @param payload the registration request payload containing user details.
     * @return HTTP 200 OK if the user is registered successfully.
     */
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserRequestPayload payload) {
        logger.info("Registering new user with username: {}", payload.username());
        userService.registerUser(payload);
        logger.info("User registered successfully with username: {}", payload.username());
        return ResponseEntity.ok().build();
    }
    
    /**
     * List all users.
     * The user must be ADMIN
     * @return a list of all registered users.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listAllUsers() {
        logger.info("Listing all users");
        List<User> users = userService.listAllUsers();
        logger.info("Listed {} users", users.size());
        return ResponseEntity.ok().body(users);
    }
}
