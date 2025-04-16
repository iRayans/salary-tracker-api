package com.rayan.salarytracker.dto.basesalary;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class BaseSalaryInsertDTO {
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    public BigDecimal amount;
    @NotNull(message = "Description is required")
    public String description;
    public boolean isActive;

    public BaseSalaryInsertDTO(BigDecimal amount, String description, boolean isActive) {
        this.amount = amount;
        this.description = description;
        this.isActive = isActive;
    }

    public BaseSalaryInsertDTO() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
