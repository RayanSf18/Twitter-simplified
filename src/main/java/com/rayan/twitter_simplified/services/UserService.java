package com.rayan.twitter_simplified.services;

import com.rayan.twitter_simplified.domain.role.Role;
import com.rayan.twitter_simplified.domain.user.User;
import com.rayan.twitter_simplified.domain.user.dto.RegisterUserRequestPayload;
import com.rayan.twitter_simplified.exceptions.custom.UserAlreadyExistsException;
import com.rayan.twitter_simplified.exceptions.custom.UserNotFoundException;
import com.rayan.twitter_simplified.repositories.RoleRepository;
import com.rayan.twitter_simplified.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Service responsible for managing users.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     *
     * @param payload the user registration details.
     */
    @Transactional
    public void registerUser(RegisterUserRequestPayload payload) {
        if (userRepository.existsByUsername(payload.username())) {
            logger.warn("Attempt to register user with existing username: {}", payload.username());
            throw new UserAlreadyExistsException("This user is already registered in the system.");
        }

        User newUser = buildUserFromDTO(payload);
        userRepository.save(newUser);
        logger.info("User registered successfully with username: {}", payload.username());
    }

    /**
     * Lists all registered users.
     *
     * @return list of users.
     */
    public List<User> listAllUsers() {
        List<User> users = userRepository.findAll();
        logger.info("Retrieved {} users from the database.", users.size());
        return users;
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the user's ID.
     * @return the user.
     */
    public User findUserById(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("User not found with id: {}", id);
                return new UserNotFoundException("User not found with id: " + id);
            });
        logger.debug("Retrieved user with id: {}", id);
        return user;
    }

    /**
     * Checks if a user has admin role.
     *
     * @param id the user's ID.
     * @return true if the user is an admin, false otherwise.
     */
    public boolean isUserAdmin(UUID id) {
        User user = findUserById(id);
        boolean isAdmin = user.getRoles().stream()
            .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
        logger.debug("User with id: {} is {}admin.", id, isAdmin ? "" : "not ");
        return isAdmin;
    }

    private User buildUserFromDTO(RegisterUserRequestPayload payload) {
        var baicRole = roleRepository.findByName(Role.Values.BASIC.name());
        User newUser = new User();
        newUser.setUsername(payload.username());
        newUser.setPassword(passwordEncoder.encode(payload.password()));
        newUser.setRoles(Set.of(baicRole));
        return newUser;
    }

}
