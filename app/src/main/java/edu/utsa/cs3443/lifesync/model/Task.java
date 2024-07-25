package edu.utsa.cs3443.lifesync.model;

import java.util.ArrayList;
public class Task extends Widget {
    private String reminderDayBefore;
    private String reminderTimeBefore;
    private String repeatDay;
    private String repeatTime;
    private long startTime;
    private long endTime;

    public Task(String id, String title, String color, String description,
                String reminderDayBefore, String reminderTimeBefore, String repeatDay, String repeatTime, long startTime, long endTime) {
        super(id, title, color, description);
        this.reminderDayBefore = reminderDayBefore;
        this.reminderTimeBefore = reminderTimeBefore;
        this.repeatDay = repeatDay;
        this.repeatTime = repeatTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public String getReminderDayBefore() {
        return reminderDayBefore;
    }

    public void setReminderDayBefore(String reminderDayBefore) {
        this.reminderDayBefore = reminderDayBefore;
    }

    public String getReminderTimeBefore() {
        return reminderTimeBefore;
    }

    public void setReminderTimeBefore(String reminderTimeBefore) {
        this.reminderTimeBefore = reminderTimeBefore;
    }

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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getType() {
        return "Task";
    }
}
