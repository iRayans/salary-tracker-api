package com.rayan.salarytracker.mapper;

import com.rayan.salarytracker.core.enumuartion.UserRole;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryInsertDTO;
import com.rayan.salarytracker.dto.basesalary.BaseSalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.category.CategoryInsertDTO;
import com.rayan.salarytracker.dto.category.CategoryReadOnlyDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseInsertDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.BaseSalary;
import com.rayan.salarytracker.model.Category;
import com.rayan.salarytracker.model.RecurringExpense;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.security.PasswordUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Mapper {

    // ======================================
    // =          User Mapper               =
    // ======================================

    public User mapToUser(UserInsertDTO userInsertDTO) {
        return new User(
                null,
                userInsertDTO.getUsername(),
                userInsertDTO.getEmail(),
                PasswordUtil.encryptPassword(userInsertDTO.getPassword()),
                UserRole.valueOf(userInsertDTO.getRole()),
                null,
                null
        );
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }


    // ============================================
    // =          BaseSalary Mapper               =
    // ============================================

    public BaseSalary mapToBaseSalary(BaseSalaryInsertDTO baseSalaryInsertDTO) {
        return new BaseSalary(
                null,
                baseSalaryInsertDTO.getAmount(),
                baseSalaryInsertDTO.getDescription(),
                null,
                baseSalaryInsertDTO.isActive,
                null,
                null
        );
    }

    public BaseSalaryReadOnlyDTO mapToBaseSalaryReadOnlyDTO(BaseSalary baseSalary) {
        return new BaseSalaryReadOnlyDTO(
                baseSalary.getId(),
                baseSalary.getAmount(),
                baseSalary.getDescription(),
                baseSalary.getActive()
        );
    }

    // ==========================================
    // =          Category Mapper               =
    // ==========================================

    public Category mapToCategory(CategoryInsertDTO categoryInsertDTO) {
        return new Category(
                null,
                categoryInsertDTO.getName(),
                categoryInsertDTO.getDescription(),
                null,
                null,
                null
        );
    }

    public CategoryReadOnlyDTO mapToCategoryReadOnlyDTO(Category category) {
        return new CategoryReadOnlyDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    // ==================================================
    // =          RecurringExpense Mapper               =
    // ==================================================

    public RecurringExpense mapToRecurringExpense(RecurringExpenseInsertDTO recurringExpenseInsertDTO) {
        return new RecurringExpense(
                null,
                recurringExpenseInsertDTO.getName(),
                recurringExpenseInsertDTO.getDescription(),
                recurringExpenseInsertDTO.getAmount(),
                recurringExpenseInsertDTO.getDueDayOfMonth(),
                recurringExpenseInsertDTO.getActive(),
                null,
                null,
                null,
                null
        );
    }

    public RecurringExpenseReadOnlyDTO mapToRecurringExpenseReadOnlyDTO(RecurringExpense recurringExpense) {
        CategoryReadOnlyDTO categoryDTO = new CategoryReadOnlyDTO(
                recurringExpense.getCategory().getId(),
                recurringExpense.getCategory().getName(),
                recurringExpense.getCategory().getDescription()
        );
        return new RecurringExpenseReadOnlyDTO(
                recurringExpense.getId(),
                recurringExpense.getName(),
                recurringExpense.getDescription(),
                recurringExpense.getAmount(),
                recurringExpense.getDueDayOfMonth(),
                recurringExpense.isActive(),
                categoryDTO,
                recurringExpense.getCreatedAt(),
                recurringExpense.getUpdatedAt()
        );
    }
}
