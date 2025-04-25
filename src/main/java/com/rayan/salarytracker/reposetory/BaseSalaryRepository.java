package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.BaseSalary;
import com.rayan.salarytracker.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BaseSalaryRepository implements PanacheRepository<BaseSalary> {

    public List<BaseSalary> getAllSalaries(Long userId) {
        return find("user.id", userId).list();
    }

    public BaseSalary findByIdAndUserId(Long salaryId, Long userId) {
        return find("id = ?1 and user.id = ?2", salaryId, userId).firstResult();
    }

    public BaseSalary findUserActiveSalary(Long userId) {
        return find("user.id = ?1 and isActive = true", userId).firstResult();
    }
}
