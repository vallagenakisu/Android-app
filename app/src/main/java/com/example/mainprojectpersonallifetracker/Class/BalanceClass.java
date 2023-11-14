package com.example.mainprojectpersonallifetracker.Class;

public class BalanceClass {
    private int PreviousBalance;
    private int CurrentBalance;
    private int LastTransaction;

    public BalanceClass(int previousBalance, int currentBalance, int lastTransaction) {
        PreviousBalance = previousBalance;
        CurrentBalance = currentBalance;
        LastTransaction = lastTransaction;
    }
    public BalanceClass()
    {

    }

    public int getPreviousBalance() {
        return PreviousBalance;
    }

    public void setPreviousBalance(int previousBalance) {
        PreviousBalance = previousBalance;
    }

    public int getCurrentBalance() {
        return CurrentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        CurrentBalance = currentBalance;
    }

    public int getLastTransaction() {
        return LastTransaction;
    }

    public void setLastTransaction(int lastTransaction) {
        LastTransaction = lastTransaction;
    }
}
