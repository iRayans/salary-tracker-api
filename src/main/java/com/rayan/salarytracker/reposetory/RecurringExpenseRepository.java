package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.RecurringExpense;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecurringExpenseRepository implements PanacheRepository<RecurringExpense> {
}
