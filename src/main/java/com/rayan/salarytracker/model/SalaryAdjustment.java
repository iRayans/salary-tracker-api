package com.rayan.salarytracker.model;

import com.rayan.salarytracker.core.enumuartion.AdjustmentType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Table(name = "salary_adjustments")
public class SalaryAdjustment extends PanacheEntity {

    @NotNull(message = "Amount is required")
    public BigDecimal amount; // Can be positive (bonus) or negative (deduction)

    @NotNull(message = "Date is required")
    public LocalDate date;

    @Column(name = "year_month")
    public YearMonth yearMonth;

    public String description;

    @Enumerated(EnumType.STRING)
    public AdjustmentType type = AdjustmentType.BONUS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @CreationTimestamp
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;


    // Lifecycle methods
    @PrePersist
    @PreUpdate
    void calculateYearMonth() {
        if (date != null) {
            this.yearMonth = YearMonth.from(date);
        }
    }

    public SalaryAdjustment() {
    }

    public SalaryAdjustment(BigDecimal amount, LocalDate date, YearMonth yearMonth, String description, AdjustmentType type, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.amount = amount;
        this.date = date;
        this.yearMonth = yearMonth;
        this.description = description;
        this.type = type;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdjustmentType getType() {
        return type;
    }

    public void setType(AdjustmentType type) {
        this.type = type;
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
        return "SalaryAdjustment{" +
                "amount=" + amount +
                ", date=" + date +
                ", yearMonth=" + yearMonth +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}