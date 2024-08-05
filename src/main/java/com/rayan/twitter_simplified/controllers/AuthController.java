package com.rayan.twitter_simplified.controllers;

import com.rayan.twitter_simplified.dto.LoginUserRequest;
import com.rayan.twitter_simplified.dto.LoginResponse;
import com.rayan.twitter_simplified.services.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for handling authentication-related requests.
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Handles user login requests.
     *
     * @param request the login request containing username and password.
     * @return the login response containing the JWT token and the time of expires.
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginUserRequest request) {
        logger.info("Login attempt for user: {}", request.username());
        LoginResponse response = authService.login(request);
        logger.info("Login successful for user: {}", request.username());
        return ResponseEntity.ok().body(response);
    }
}
