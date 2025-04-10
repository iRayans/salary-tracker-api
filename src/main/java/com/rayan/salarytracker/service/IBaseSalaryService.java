package com.rayan.salarytracker.service;

import com.rayan.salarytracker.dto.basesalary.BaseSalaryInsertDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;

import java.util.List;

public interface IBaseSalaryService {
    BaseSalaryReadOnlyDTO save(BaseSalaryInsertDTO baseSalary);
    List<BaseSalaryReadOnlyDTO> getSalaries(Long userId);
}
