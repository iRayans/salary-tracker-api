package com.rayan.salarytracker.model;


import com.rayan.salarytracker.core.enumuartion.CategoryType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    // Budget limit for this category
    @Column(name = "monthly_budget")
    private BigDecimal monthlyBudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private CategoryType type = CategoryType.EXPENSE;


    public Category() {
    }

    public Category(Long id, String name, String description, BigDecimal monthlyBudget, User user, LocalDateTime createdAt, LocalDateTime updatedAt, CategoryType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.monthlyBudget = monthlyBudget;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
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

    public BigDecimal getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(BigDecimal monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
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

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", monthlyBudget=" + monthlyBudget +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", type=" + type +
                '}';
    }
}