package com.example.mainprojectpersonallifetracker.Class;

public class TodoClass {
    private String task;
    private int status;
    private String id;


    public TodoClass()
    {

    }
    public TodoClass(String task, int status , String id) {
        this.task = task;
        this.status = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
