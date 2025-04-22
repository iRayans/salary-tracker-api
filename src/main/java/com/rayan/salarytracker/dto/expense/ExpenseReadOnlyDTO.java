package com.rayan.salarytracker.dto.expense;

import com.rayan.salarytracker.dto.category.CategoryReadOnlyDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseReadOnlyDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ExpenseReadOnlyDTO {

    private Long id;
    private String name;
    private String description;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;
    private YearMonth yearMonth;
    private Boolean isPaid;
    private String bank;
    private CategoryReadOnlyDTO category;
    private  Long recurringId;

    public ExpenseReadOnlyDTO(Long id, String name, String description, BigDecimal amount, YearMonth yearMonth, Boolean isPaid, String bank, CategoryReadOnlyDTO category, Long recurringId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.yearMonth = yearMonth;
        this.isPaid = isPaid;
        this.bank = bank;
        this.category = category;
        this.recurringId = recurringId;
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

    public CategoryReadOnlyDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryReadOnlyDTO category) {
        this.category = category;
    }

    public Long getRecurringId() {
        return recurringId;
    }

    public void setRecurringId(Long recurringId) {
        this.recurringId = recurringId;
    }

    @Override
    public String toString() {
        return "ExpenseReadOnlyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", yearMonth=" + yearMonth +
                ", isPaid=" + isPaid +
                ", bank='" + bank + '\'' +
                ", recurringId=" + recurringId +
                '}';
    }
}
