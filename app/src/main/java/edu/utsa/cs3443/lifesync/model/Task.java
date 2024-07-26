package edu.utsa.cs3443.lifesync.model;

import java.time.LocalTime;

public class Task extends Widget {
    private String taskDate;
    private LocalTime reminderTimeBefore;
    private LocalTime repeatTime;
    private LocalTime startTime;
    

    public Task(String id, String title, String color, String description, String taskDate, LocalTime reminderTimeBefore, LocalTime repeatTime, LocalTime startTime) {
        super(id, title, color, description);
        this.taskDate = taskDate;
        this.reminderTimeBefore =reminderTimeBefore;
        this.repeatTime = repeatTime;
        this.startTime = startTime;
    }
    public Task(String id, String title, String color, String description, String taskDate,LocalTime reminderTimeBefore, LocalTime startTime) {
        super(id, title, color, description);
        this.taskDate = taskDate;
        this.reminderTimeBefore =reminderTimeBefore;
        this.startTime = startTime;
    }


    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
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
