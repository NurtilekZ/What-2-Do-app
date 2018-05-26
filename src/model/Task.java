package model;

import java.sql.Date;

public class Task {

    private int userId;
    private String task;
    private Date date;
    private String location;
    private String notes;

    public Task() {
    }

    public Task(Integer userId, String task, Date date, String location, String notes) {
        this.userId = userId;
        this.task = task;
        this.date = date;
        this.location = location;
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
