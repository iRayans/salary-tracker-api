package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.dto.basesalary.BaseSalaryInsertDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.BaseSalary;
import com.rayan.salarytracker.reposetory.BaseSalaryRepository;
import com.rayan.salarytracker.service.IBaseSalaryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class BaseSalaryService implements IBaseSalaryService {

    @Inject
    BaseSalaryRepository baseSalaryRepository;
    @Inject
    Mapper mapper;
    @Override
    public BaseSalaryReadOnlyDTO save(BaseSalaryInsertDTO baseSalaryInsertDTO) {
        BaseSalary baseSalary = mapper.mapToBaseSalary(baseSalaryInsertDTO);
        baseSalaryRepository.persist(baseSalary);
        return mapper.mapToBaseSalaryReadOnlyDTO(baseSalary);
    }

    @Override
    public List<BaseSalaryReadOnlyDTO> getSalaries(Long userId) {
        List<BaseSalary> salaries = baseSalaryRepository.getAllSalaries(userId);
        List<BaseSalaryReadOnlyDTO> s = new ArrayList<>();
        for (BaseSalary salary : salaries) {
            s.add(mapper.mapToBaseSalaryReadOnlyDTO(salary));
        }
        return s;
    }
}
