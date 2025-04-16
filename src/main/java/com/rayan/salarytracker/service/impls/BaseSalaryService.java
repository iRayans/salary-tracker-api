package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryInsertDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryUpdateDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.BaseSalary;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.BaseSalaryRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import com.rayan.salarytracker.service.IBaseSalaryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class BaseSalaryService implements IBaseSalaryService {
    private static final Logger LOGGER = Logger.getLogger(BaseSalaryService.class);


    @Inject
    BaseSalaryRepository baseSalaryRepository;
    @Inject
    Mapper mapper;
    @Inject
    LoggedInUser loggedInUser;


    @Override
    public BaseSalaryReadOnlyDTO save(BaseSalaryInsertDTO baseSalaryInsertDTO) {

        // Get current user
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create salary: User not found"));

        // Map and save the salary
        BaseSalary baseSalary = mapper.mapToBaseSalary(baseSalaryInsertDTO);
        baseSalary.setUser(currentUser);

        // No try-catch needed here, let any exceptions propagate up
        baseSalaryRepository.persist(baseSalary);

        return mapper.mapToBaseSalaryReadOnlyDTO(baseSalary);
    }

    @Override
    public List<BaseSalaryReadOnlyDTO> getSalaries() {
        Long userId = loggedInUser.getUserId();
        LOGGER.debug("Fetching salaries for user ID: {}" + loggedInUser.getUserId());
        List<BaseSalary> salaries = baseSalaryRepository.getAllSalaries(userId);
        LOGGER.debug("Found {} salary records" + salaries.size());

        return salaries.stream()
                .map(mapper::mapToBaseSalaryReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BaseSalaryReadOnlyDTO getById(Long salaryId) {
        BaseSalary baseSalary = baseSalaryRepository.findByIdAndUserId(salaryId, loggedInUser.getUserId());
        if (baseSalary == null) {
            throw new NotFoundException("Salary record not found or you don't have permission to access it");
        }
        return mapper.mapToBaseSalaryReadOnlyDTO(baseSalary);
    }

    @Override
    public BaseSalaryReadOnlyDTO updateSalary(Long salaryId, BaseSalaryUpdateDTO updateDTO) {
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create salary: User not found"));
        BaseSalary existingSalary = baseSalaryRepository.findByIdAndUserId(salaryId, currentUser.getId());

        if (existingSalary == null) {
            throw new NotFoundException("Salary record not found with the given ID for your account");
        }

        // update fields
        updateFields(existingSalary, updateDTO);
        // Log the update
        LOGGER.info("User " + currentUser.getUsername() + " updated salary record " + salaryId);

        return mapper.mapToBaseSalaryReadOnlyDTO(existingSalary);
    }


    private void updateFields(BaseSalary existingSalary, BaseSalaryUpdateDTO updateDTO) {
        existingSalary.setAmount(updateDTO.getAmount() != null ? updateDTO.getAmount() : existingSalary.getAmount());
        existingSalary.setDescription(updateDTO.getDescription() != null ? updateDTO.getDescription() : existingSalary.getDescription());
        existingSalary.setActive(updateDTO.isActive() != null ? updateDTO.isActive() : existingSalary.getActive());
    }
}

