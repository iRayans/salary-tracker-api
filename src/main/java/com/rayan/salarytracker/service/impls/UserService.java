package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.UserRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import com.rayan.salarytracker.service.IUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;


@ApplicationScoped
public class UserService implements IUserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Inject
    UserRepository userRepository;
    @Inject
    Mapper mapper;
    @Inject
    LoggedInUser loggedInUser;
    @Override

    @Transactional
    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) {
        LOGGER.info("Creating user: " + userInsertDTO);
        if (userInsertDTO.getRole() == null) {
            userInsertDTO.setRole("USER");
        }
        User user = mapper.mapToUser(userInsertDTO);
        userRepository.persist(user);
        return mapper.mapToUserReadOnlyDTO(user);
    }

    @Override
    public UserReadOnlyDTO findUserByEmail(String email) {
        LOGGER.info("Finding user by email: " + email);
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
        LOGGER.info("Checking if user with email: " + email + " exists");
        return userRepository.isEmailExists(email);
    }

    @Override
    public Boolean isUserValid(String email, String password) {
        LOGGER.info("Checking if user with email: " + email + " exists");
       return userRepository.isUserValid(email, password);
    }

    public UserReadOnlyDTO loginUser( ) {
        User user = loggedInUser.getUser().orElseThrow(() -> new EntityNotFoundException("User not found"));
        return mapper.mapToUserReadOnlyDTO(user);
    }

    @Override
    public Boolean isUsernameExists(String username) {
        return userRepository.isUsernameExists(username);
    }
}
