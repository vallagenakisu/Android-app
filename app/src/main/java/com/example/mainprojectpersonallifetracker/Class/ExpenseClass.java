package com.example.mainprojectpersonallifetracker.Class;

public class ExpenseClass {
    int amount;
    String Description;
    String type;

    public ExpenseClass(int amount, String description, String type) {
        this.amount = amount;
        Description = description;
        this.type = type;
    }
    public ExpenseClass()
    {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
