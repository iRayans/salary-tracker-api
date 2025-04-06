package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.UserRepository;
import com.rayan.salarytracker.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class UserService implements IUserService {

    @Inject
    UserRepository userRepository;
    @Inject
    Mapper mapper;

    @Override
    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) {

        if (userInsertDTO.getRole() == null) {
            userInsertDTO.setRole("USER");
        }
        User user = mapper.mapToUser(userInsertDTO);
        userRepository.persist(user);
        return mapper.mapToUserReadOnlyDTO(user);
    }

    @Override
    public UserReadOnlyDTO findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return mapper.mapToUserReadOnlyDTO(user);
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public UserReadOnlyDTO getUserById(Long id) {
        return null;
    }

    @Override
    public Boolean isEmailExists(String email) {
        return userRepository.isEmailExists(email);
    }

    @Override
    public Boolean isUserValid(String email, String password) {
       return userRepository.isUserValid(email, password);
    }
}
