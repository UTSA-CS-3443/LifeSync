package edu.utsa.cs3443.lifesync.model;
import java.util.ArrayList;


public class Event extends Widget {
    private String location;
    private ArrayList<String> guests;
    private String reminderDayBefore;
    private String reminderTimeBefore;
    private String repeatDay;
    private String repeatTime;
    private long startTime;
    private long endTime;

    public Event(int id, String title, String description, String color, String location, ArrayList<String> guests,
                 String reminderDayBefore, String reminderTimeBefore, String repeatDay, String repeatTime, long startTime, long endTime) {
        super(id, title, description, color);
        this.location = location;
        this.guests = guests;
        this.reminderDayBefore = reminderDayBefore;
        this.reminderTimeBefore = reminderTimeBefore;
        this.repeatDay = repeatDay;
        this.repeatTime = repeatTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<String> guests) {
        this.guests = guests;
    }

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
        return "Event";
    }
}
