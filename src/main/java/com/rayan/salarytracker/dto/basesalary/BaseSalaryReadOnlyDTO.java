package com.rayan.salarytracker.dto.basesalary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BaseSalaryReadOnlyDTO {

    private Long id;
    public BigDecimal amount;
    public String description;
    public boolean isActive;

    public BaseSalaryReadOnlyDTO(Long id, BigDecimal amount, String description, boolean isActive) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.isActive = isActive;
    }

    public BaseSalaryReadOnlyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @JsonProperty("isActive")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "BaseSalaryReadOnlyDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
