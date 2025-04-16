package com.rayan.salarytracker.dto.basesalary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BaseSalaryUpdateDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private Boolean isActive;

    public BaseSalaryUpdateDTO() {
    }


    public BaseSalaryUpdateDTO(Long id, String description, BigDecimal amount, Boolean isActive) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JsonProperty("isActive")
    public Boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "BaseSalaryUpdateDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", isActive=" + isActive +
                '}';
    }
}
