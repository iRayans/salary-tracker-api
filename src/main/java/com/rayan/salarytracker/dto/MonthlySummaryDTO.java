package com.rayan.salarytracker.dto;

import java.math.BigDecimal;

public class MonthlySummaryDTO {
    private BigDecimal totalExpenses;
    private BigDecimal salary;
    private String yearMonth;
    private BigDecimal remaining;

    public MonthlySummaryDTO() {
    }

    public MonthlySummaryDTO(BigDecimal totalExpenses, BigDecimal salary, String yearMonth, BigDecimal remaining) {
        this.totalExpenses = totalExpenses;
        this.salary = salary;
        this.yearMonth = yearMonth;
        this.remaining = remaining;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "MonthlySummaryDTO{" +
                "totalExpenses=" + totalExpenses +
                ", salary=" + salary +
                ", yearMonth='" + yearMonth + '\'' +
                ", remaining=" + remaining +
                '}';
    }
}
