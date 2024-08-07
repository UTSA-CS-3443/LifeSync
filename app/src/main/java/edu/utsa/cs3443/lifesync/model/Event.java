package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Event class represents an event within the LifeSync application.
 * It includes details such as location, guests, event date, reminder time, and start time.
 */
public class Event extends Widget implements Serializable {
    private String location;
    private ArrayList<String> guests;
    private Date eventDate;
    private LocalTime reminderTimeBefore;
    private LocalTime startTime;

    /**
     * Constructs a new Event with the specified details.
     *
     * @param id                The unique identifier for the event.
     * @param title             The title of the event.
     * @param color             The color associated with the event.
     * @param description       The description of the event.
     * @param location          The location of the event.
     * @param guests            The guests attending the event.
     * @param eventDate         The date of the event.
     * @param reminderTimeBefore The reminder time before the event.
     * @param startTime         The start time of the event.
     */
    public Event(String id, String title, String color, String description, String location, ArrayList<String> guests, Date eventDate, LocalTime reminderTimeBefore, LocalTime startTime) {
        super(id, title, color, description);
        this.location = location;
        this.guests = guests;
        this.eventDate = eventDate;
        this.reminderTimeBefore = reminderTimeBefore;
        this.startTime = startTime;
    }

    /**
     * Gets the location of the event.
     *
     * @return The location of the event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the event.
     *
     * @param location The location of the event.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the guests attending the event.
     *
     * @return The list of guests.
     */
    @Override
    public ArrayList<String> getGuests() {
        return guests;
    }

    /**
     * Sets the guests attending the event.
     *
     * @param guests The list of guests.
     */
    public void setGuests(ArrayList<String> guests) {
        this.guests = guests;
    }

    /**
     * Gets the formatted date of the event.
     *
     * @return The formatted date string.
     */
    @Override
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(eventDate);
    }

    /**
     * Gets the date of the event.
     *
     * @return The date of the event.
     */
    @Override
    public Date getDate() {
        return eventDate;
    }

    /**
     * Sets the date of the event.
     *
     * @param eventDate The date of the event.
     */
    public void setDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Gets the reminder time before the event.
     *
     * @return The reminder time before the event.
     */
    public LocalTime getReminderTimeBefore() {
        return reminderTimeBefore;
    }

    /**
     * Sets the reminder time before the event.
     *
     * @param reminderTimeBefore The reminder time before the event.
     */
    public void setReminderTimeBefore(LocalTime reminderTimeBefore) {
        this.reminderTimeBefore = reminderTimeBefore;
    }

    /**
     * Gets the formatted start time of the event.
     *
     * @return The formatted start time string.
     */
    @Override
    public String getFormattedStartTime() {
        return startTime.toString();
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the event.
     *
     * @param startTime The start time of the event.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the type of the widget, which is "Event".
     *
     * @return The type of the widget.
     */
    @Override
    public String getType() {
        return "Event";
    }
}
