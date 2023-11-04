package com.example.mainprojectpersonallifetracker.Class;

public class TodoClass {
    private String task;
    private int status;

    public TodoClass()
    {

    }
    public TodoClass(String task, int status) {
        this.task = task;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
