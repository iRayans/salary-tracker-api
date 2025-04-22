package com.rayan.salarytracker.dto.expense;

import java.math.BigDecimal;

public class ExpenseUpdateDTO {

    private String name;
    private String description;
    private BigDecimal amount;
    private Boolean isPaid;
    private String bank;

    public ExpenseUpdateDTO() {
    }

    public ExpenseUpdateDTO(String name, String description, BigDecimal amount, Boolean isPaid, String bank) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.isPaid = isPaid;
        this.bank = bank;
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

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
