package edu.utsa.cs3443.lifesync.model;

import java.time.LocalTime;

public class Task extends Widget {
    private String taskDate;
    private String repeatDay;
    private String repeatTime;
    private LocalTime startTime;
    private LocalTime endTime;
    

    public Task(String id, String title, String color, String description, String taskDate, String repeatDay, String repeatTime, LocalTime startTime, LocalTime endTime) {
        super(id, title, color, description);
        this.taskDate = taskDate;
        this.repeatDay = repeatDay;
        this.repeatTime = repeatTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
// Getters and Setters


    public String getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(String repeatDay) {
        this.repeatDay = repeatDay;
    }

    public String getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(String repeatTime) {
        this.repeatTime = repeatTime;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getType() {
        return "Task";
    }
}
