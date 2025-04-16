package com.rayan.salarytracker.security;

import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.Optional;

@RequestScoped
public class LoggedInUser {
    private static final Logger LOGGER = Logger.getLogger(LoggedInUser.class);

    @Inject
    JsonWebToken jwt;

    @Inject
    UserRepository userRepository;
    private User cachedUser;

    public Long getUserId() {
        // Cast claim `JsonNumber → Long`
        return ((JsonNumber) jwt.getClaim("userId")).longValue();
    }

    public String getUserEmail() {
        return jwt.getSubject();
    }

    public boolean hasRole(String role) {
        return jwt.getGroups().contains(role);
    }

    public Optional<User> getUser() {
        return Optional.ofNullable(userRepository.findById(getUserId()));
    }
}