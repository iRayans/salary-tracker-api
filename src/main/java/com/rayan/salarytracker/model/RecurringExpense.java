package com.rayan.salarytracker.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "recurring_expenses")
public class RecurringExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    public String name;

    public String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    public BigDecimal amount;

    @Min(value = 1, message = "Day of month must be at least 1")
    @Max(value = 31, message = "Day of month must be at most 31")
    @Column(name = "due_day_of_month")
    public Integer dueDayOfMonth;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date")
    public LocalDate startDate;

    @Column(name = "is_active")
    public boolean isActive = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    public Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @CreationTimestamp
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    public RecurringExpense() {
    }

    public RecurringExpense(Long id, String name, String description, BigDecimal amount, Integer dueDayOfMonth, LocalDate startDate, boolean isActive, Category category, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.dueDayOfMonth = dueDayOfMonth;
        this.startDate = startDate;
        this.isActive = isActive;
        this.category = category;
        this.user = user;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "RecurringExpense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", dueDayOfMonth=" + dueDayOfMonth +
                ", startDate=" + startDate +
                ", isActive=" + isActive +
                ", category=" + category +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}