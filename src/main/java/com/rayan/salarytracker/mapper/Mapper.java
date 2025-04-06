package com.rayan.salarytracker.mapper;

import com.rayan.salarytracker.core.enumuartion.UserRole;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.security.PasswordUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Mapper {

    // ======================================
    // =          User Mapper               =
    // ======================================

    public User mapToUser(UserInsertDTO userInsertDTO) {
        return new User(null,
                userInsertDTO.getUsername(),
                userInsertDTO.getEmail(),
                PasswordUtil.encryptPassword(userInsertDTO.getPassword()),
                UserRole.valueOf(userInsertDTO.getRole()),
                null,
                null
        );
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
