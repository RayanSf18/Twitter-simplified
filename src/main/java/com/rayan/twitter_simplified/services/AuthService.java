package com.rayan.twitter_simplified.services;

import com.rayan.twitter_simplified.domain.role.Role;
import com.rayan.twitter_simplified.domain.user.User;
import com.rayan.twitter_simplified.dto.LoginResponse;
import com.rayan.twitter_simplified.dto.LoginUserRequest;
import com.rayan.twitter_simplified.exceptions.custom.CredentialsInvalidException;
import com.rayan.twitter_simplified.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service responsible for authentication operations.
 */
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtEncoder jwtEncoder, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param request the login request containing username and password.
     * @return the login response with JWT token and expiration time.
     * @throws CredentialsInvalidException if the credentials are invalid.
     */
    public LoginResponse login(LoginUserRequest request) {
        logger.info("Attempting login for user: {}", request.username());

        var user = findUserByUsername(request.username())
            .filter(u -> isLoginValid(u, request))
            .orElseThrow(() -> {
                logger.warn("Invalid credentials for user: {}", request.username());
                return new CredentialsInvalidException("Invalid credentials: Check whether your username or password is correct.");
            });

        String jwtToken = generateJwtToken(user);
        logger.info("Successfully logged in user: {}", request.username());

        return new LoginResponse(jwtToken, 300L);
    }

    private String generateJwtToken(User user) {
        var scopes = user.getRoles()
            .stream()
            .map(Role::getName)
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("twitter-backend")
            .subject(user.getId().toString())
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(300L))
            .claim("scope", scopes)
            .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean isLoginValid(User user, LoginUserRequest request) {
        return user.isLoginCorrect(request, passwordEncoder);
    }
}
