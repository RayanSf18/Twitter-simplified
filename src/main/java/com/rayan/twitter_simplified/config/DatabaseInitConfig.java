package com.rayan.twitter_simplified.config;

import com.rayan.twitter_simplified.domain.role.Role;
import com.rayan.twitter_simplified.domain.user.User;
import com.rayan.twitter_simplified.repositories.RoleRepository;
import com.rayan.twitter_simplified.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Set;

/**
 * Configuration class for initializing the database with default roles and admin user.
 */
@Configuration
public class DatabaseInitConfig implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DatabaseInitConfig.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.admin.password}")
    private String adminPassword;

    public DatabaseInitConfig(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        initializeRoles();
        initializeAdminUser();

        stopWatch.stop();
        logger.info("Database initialization completed in {} ms", stopWatch.getTotalTimeMillis());
    }

    /**
     * Initialize the roles in the database.
     */
    private void initializeRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(Arrays.asList(
                new Role(1L, Role.Values.ADMIN.name()),
                new Role(2L, Role.Values.BASIC.name())
            ));
            logger.info("Roles initialized.");
        } else {
            logger.info("Roles already exist, skipping initialization.");
        }
    }

    /**
     * Initialize the admin user in the database.
     */
    private void initializeAdminUser() {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
            user -> logger.info("Admin user already exists, skipping initialization"),
            () -> {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode(adminPassword));
                adminUser.setRoles(Set.of(roleAdmin));
                userRepository.save(adminUser);
                logger.info("Admin user created.");
            }
        );
    }

}
