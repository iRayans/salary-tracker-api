package com.rayan.salarytracker.security;

import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import io.smallrye.jwt.build.Jwt;
import java.time.Duration;

import java.util.HashSet;
import java.util.Set;

public class JwtService {

    /**
     * A utility class to generate a JWT token.
     */

    public static String generateToken(UserReadOnlyDTO user) {
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole());
        return Jwt.issuer("my-auth-service")
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .groups(roles)
                .expiresIn(Duration.ofHours(2)) // Token valid for 2 hours
                .sign();
    }
}
