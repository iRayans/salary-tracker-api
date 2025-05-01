package com.rayan.salarytracker.service;

import com.rayan.salarytracker.dto.basesalary.BaseSalaryInsertDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryUpdateDTO;

import java.util.List;

public interface IBaseSalaryService {
    BaseSalaryReadOnlyDTO save(BaseSalaryInsertDTO baseSalary);
    List<BaseSalaryReadOnlyDTO> getSalaries();
    BaseSalaryReadOnlyDTO getById(Long salaryId);
    BaseSalaryReadOnlyDTO updateSalary(Long salaryId, BaseSalaryUpdateDTO updateDTO);
    void deleteSalary(Long salaryId);
    BaseSalaryReadOnlyDTO getActiveSalary();

}
