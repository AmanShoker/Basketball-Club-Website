package com.example;

public class IncomeStatement {
    private double revenue;
    private double expenses;

    public void recordRevenue(double amount) {
        this.revenue += amount;
    }

    public void recordExpense(double amount) {
        this.expenses += amount;
    }

    public double calculateProfit() {
        return revenue - expenses;
    }
}