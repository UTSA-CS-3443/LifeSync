package edu.utsa.cs3443.lifesync.model;
import java.util.ArrayList;


public class Event extends Widget {
    private String location;
    private ArrayList<String> guests;
    private String eventDate;
    private String reminderTimeBefore;
    private String repeatDay;
    private long startTime;
    private long endTime;
    private Notification notification;

    public Event(String id, String title, String color, String description, String location, ArrayList<String> guests,  String eventDate, String reminderTimeBefore, String repeatDay, long startTime, long endTime) {
        super(id, title, color, description);
        this.location = location;
        this.guests = guests;
        this.eventDate = eventDate;
        this.reminderTimeBefore = reminderTimeBefore;
        this.repeatDay = repeatDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notification = new Notification(id, title, eventDate);
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
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
