package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
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

import org.jboss.logging.Logger;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BaseSalaryService implements IBaseSalaryService {
    private static final Logger LOGGER = Logger.getLogger(BaseSalaryService.class);


    @Inject
    BaseSalaryRepository baseSalaryRepository;
    @Inject
    Mapper mapper;
    @Inject
    LoggedInUser loggedInUser;


    @Override
    @Transactional
    public BaseSalaryReadOnlyDTO save(BaseSalaryInsertDTO baseSalaryInsertDTO) {
        LOGGER.info("Saving baseSalary: " + baseSalaryInsertDTO);
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create salary: User not found"));

        BaseSalary baseSalary = mapper.mapToBaseSalary(baseSalaryInsertDTO);
        baseSalary.setUser(currentUser);

        baseSalaryRepository.persist(baseSalary);
        LOGGER.info("BaseSalary Saved.");
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
        BaseSalary baseSalary = baseSalaryRepository.findByIdAndUserId(salaryId, loggedInUser.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Salary not found"));
        if (baseSalary == null) {
            throw new EntityNotFoundException("Salary record not found.");
        }
        return mapper.mapToBaseSalaryReadOnlyDTO(baseSalary);
    }

    @Override
    @Transactional
    public BaseSalaryReadOnlyDTO updateSalary(Long salaryId, BaseSalaryUpdateDTO updateDTO) {
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create salary: User not found"));
        BaseSalary existingSalary = baseSalaryRepository.findByIdAndUserId(salaryId, currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Salary not found"));

        if (existingSalary == null) {
            throw new EntityNotFoundException("Salary record not found with the given ID for your account");
        }

        // update fields
        updateFields(existingSalary, updateDTO);
        // Log the update
        LOGGER.info("User " + currentUser.getUsername() + " updated salary record " + salaryId);

        return mapper.mapToBaseSalaryReadOnlyDTO(existingSalary);
    }

    @Override
    @Transactional
    public void deleteSalary(Long salaryId) {
        LOGGER.info("deleting salary record " + salaryId);
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create salary: User not found"));
        BaseSalary baseSalary = baseSalaryRepository.findByIdAndUserId(salaryId, currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Salary not found"));
        if (baseSalary == null) {
            throw new EntityNotFoundException("Salary record not found with the given ID for your account");
        }
        baseSalaryRepository.delete(baseSalary);
    }

    @Override
    public BaseSalaryReadOnlyDTO getActiveSalary() {
        Long userId = loggedInUser.getUserId();
        BaseSalary baseSalary = baseSalaryRepository.findUserActiveSalary(userId)
                .orElseThrow(() -> new EntityNotFoundException("Salary not found"));
        if (baseSalary == null) {
            throw new EntityNotFoundException("Salary record not found with the given ID for your account");
        }
        return mapper.mapToBaseSalaryReadOnlyDTO(baseSalary);
    }

    private void updateFields(BaseSalary existingSalary, BaseSalaryUpdateDTO updateDTO) {
        existingSalary.setAmount(updateDTO.getAmount() != null ? updateDTO.getAmount() : existingSalary.getAmount());
        existingSalary.setDescription(updateDTO.getDescription() != null ? updateDTO.getDescription() : existingSalary.getDescription());
        existingSalary.setActive(updateDTO.isActive() != null ? updateDTO.isActive() : existingSalary.getActive());
    }
}
