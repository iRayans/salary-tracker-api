package com.rayan.salarytracker.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "base_salaries")
public class BaseSalary extends PanacheEntity {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    public BigDecimal amount;

    public String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    public boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    public BaseSalary() {
    }

    public BaseSalary(BigDecimal amount, String description, User user, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.amount = amount;
        this.description = description;
        this.user = user;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
        return "BaseSalary{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
