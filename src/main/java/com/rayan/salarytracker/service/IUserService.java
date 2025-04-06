package com.rayan.salarytracker.service;

import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.User;

import java.util.List;

public interface IUserService {
    UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO);
    UserReadOnlyDTO findUserByEmail(String email);
    List<User> getUsers();
    UserReadOnlyDTO getUserById(Long id);
    Boolean isEmailExists(String email);
    Boolean isUserValid(String email, String password);
}
