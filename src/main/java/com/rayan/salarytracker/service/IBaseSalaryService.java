package com.rayan.salarytracker.service;

import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;

import java.util.List;

public interface IBaseSalaryService {
    BaseSalaryReadOnlyDTO save(BaseSalaryReadOnlyDTO baseSalary);
    List<BaseSalaryReadOnlyDTO> getSalaries(Long userId);
}
