package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Task extends Widget implements Serializable {
    private Date taskDate;
    private LocalTime reminderTimeBefore;
    private LocalTime repeatTime;
    private LocalTime startTime;
    

    public Task(String id, String title, String color, String description, Date taskDate, LocalTime reminderTimeBefore, LocalTime repeatTime, LocalTime startTime) {
        super(id, title, color, description);
        this.taskDate = taskDate;
        this.reminderTimeBefore =reminderTimeBefore;
        this.repeatTime = repeatTime;
        this.startTime = startTime;
    }
    public Task(String id, String title, String color, String description, Date taskDate,LocalTime reminderTimeBefore, LocalTime startTime) {
        super(id, title, color, description);
        this.taskDate = taskDate;
        this.reminderTimeBefore =reminderTimeBefore;
        this.startTime = startTime;
    }

    @Override
    public Date getDate() {
        return taskDate;
    }

    public void setDate(Date taskDate) {
        this.taskDate = taskDate;
    }
// Getters and Setters


    public LocalTime getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(LocalTime repeatTime) {
        this.repeatTime = repeatTime;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String getType() {
        return "Task";
    }
}
