package com.rayan.salarytracker.dto.basesalary;

import java.math.BigDecimal;

public class BaseSalaryInsertDTO {
    public BigDecimal amount;
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
