package com.rayan.salarytracker.dto.recurringExpense;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rayan.salarytracker.dto.category.CategoryReadOnlyDTO;
import com.rayan.salarytracker.model.Category;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RecurringExpenseReadOnlyDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal amount;
    private Integer dueDayOfMonth;
    private Boolean isActive;
    private CategoryReadOnlyDTO category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RecurringExpenseReadOnlyDTO() {
    }

    public RecurringExpenseReadOnlyDTO(Long id, String name, String description, BigDecimal amount, Integer dueDayOfMonth, Boolean isActive, CategoryReadOnlyDTO category, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.dueDayOfMonth = dueDayOfMonth;
        this.isActive = isActive;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    @JsonProperty("isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public CategoryReadOnlyDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryReadOnlyDTO category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "RecurringExpenseReadOnlyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", dueDayOfMonth=" + dueDayOfMonth +
                ", isActive=" + isActive +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
