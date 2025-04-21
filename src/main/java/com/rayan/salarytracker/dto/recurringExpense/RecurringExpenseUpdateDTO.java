package com.rayan.salarytracker.dto.recurringExpense;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RecurringExpenseUpdateDTO {

    private  String name;
    private String description;
    private BigDecimal amount;
    private Integer dueDayOfMonth;
    private Boolean isActive;

    public RecurringExpenseUpdateDTO(String name, String description, BigDecimal amount, Integer dueDayOfMonth, Boolean isActive) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.dueDayOfMonth = dueDayOfMonth;
        this.isActive = isActive;
    }

    public RecurringExpenseUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getDueDayOfMonth() {
        return dueDayOfMonth;
    }

    public void setDueDayOfMonth(Integer dueDayOfMonth) {
        this.dueDayOfMonth = dueDayOfMonth;
    }

    @JsonProperty("isActive")
    public Boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
