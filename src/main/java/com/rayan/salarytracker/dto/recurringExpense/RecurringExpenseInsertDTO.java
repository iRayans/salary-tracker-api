package com.rayan.salarytracker.dto.recurringExpense;

import com.rayan.salarytracker.model.Category;
import com.rayan.salarytracker.model.User;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class RecurringExpenseInsertDTO {

    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;
    @Min(value = 1, message = "Day of month must be at least 1")
    @Max(value = 31, message = "Day of month must be at most 31")
    private Integer dueDayOfMonth;
    private Boolean isActive = true;
    private User user;
    private Long categoryId;

    public RecurringExpenseInsertDTO() {
    }

    public RecurringExpenseInsertDTO(Long id, String name, String description, BigDecimal amount, Integer dueDayOfMonth, Boolean isActive, User user, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.dueDayOfMonth = dueDayOfMonth;
        this.isActive = isActive;
        this.user = user;
        this.categoryId = categoryId;
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

    public Integer getDueDayOfMonth() {
        return dueDayOfMonth;
    }

    public void setDueDayOfMonth(Integer dueDayOfMonth) {
        this.dueDayOfMonth = dueDayOfMonth;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "RecurringExpenseInsertDTO{" +
                "isActive=" + isActive +
                ", dueDayOfMonth=" + dueDayOfMonth +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
