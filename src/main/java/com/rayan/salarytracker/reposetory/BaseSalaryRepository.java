package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.BaseSalary;
import com.rayan.salarytracker.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BaseSalaryRepository implements PanacheRepository<BaseSalary> {

    public List<BaseSalary> getAllSalaries(Long userId) {
        return find("user.id", userId).list();
    }

    public Optional<BaseSalary> findByIdAndUserId(Long salaryId, Long userId) {
        return find("id = ?1 AND user.id = ?2",salaryId, userId).singleResultOptional();
    }

    public Optional<BaseSalary> findUserActiveSalary(Long userId) {
        return find("user.id = ?1 and isActive = true", userId).firstResultOptional();
    }
}
