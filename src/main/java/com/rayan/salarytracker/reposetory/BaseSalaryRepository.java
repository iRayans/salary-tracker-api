package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.BaseSalary;
import com.rayan.salarytracker.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class BaseSalaryRepository implements PanacheRepository<BaseSalary> {

    public BaseSalary findByUser(User user) {
        return find("user", user).firstResult();
    }

    public BaseSalary findByUserId(Long userId) {
        return find("user.id", userId).firstResult();
    }
}
