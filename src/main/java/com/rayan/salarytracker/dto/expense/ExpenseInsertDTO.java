package com.rayan.salarytracker.dto.expense;

import com.rayan.salarytracker.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ExpenseInsertDTO {

    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;
    private YearMonth yearMonth;
    private Boolean paid;
    private Long categoryId;
    private Long recurringExpenseId;
    private String bank;


    public ExpenseInsertDTO() {
    }

    public ExpenseInsertDTO(Long id, String name, String description, BigDecimal amount, YearMonth yearMonth, Boolean paid, Long categoryId, Long recurringExpenseId, String bank) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.yearMonth = yearMonth;
        this.paid = paid;
        this.categoryId = categoryId;
        this.recurringExpenseId = recurringExpenseId;
        this.bank = bank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getRecurringExpenseId() {
        return recurringExpenseId;
    }

    public void setRecurringExpenseId(Long recurringExpenseId) {
        this.recurringExpenseId = recurringExpenseId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "ExpenseInsertDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", yearMonth=" + yearMonth +
                ", isPaid=" + paid +
                ", categoryId=" + categoryId +
                ", recurringExpenseId=" + recurringExpenseId +
                ", bank='" + bank + '\'' +
                '}';
    }
}
