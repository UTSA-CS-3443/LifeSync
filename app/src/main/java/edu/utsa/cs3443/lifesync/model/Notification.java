package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The Notification class represents a notification within the LifeSync application.
 * Notifications can be used to inform users about events.
 */
public class Notification implements Serializable {
    private String id;
    private String title;
    private Date eventDate;

    /**
     * Constructs a new Notification with the specified details.
     *
     * @param id       The unique identifier for the notification.
     * @param title    The title of the notification.
     * @param eventDate The date of the event associated with the notification.
     */
    public Notification(String id, String title, Date eventDate) {
        this.id = id;
        this.title = title;
        this.eventDate = eventDate;
    }

    /**
     * Gets the unique identifier for the notification.
     *
     * @return The unique identifier for the notification.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the notification.
     *
     * @param id The unique identifier for the notification.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the title of the notification.
     *
     * @return The title of the notification.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the notification.
     *
     * @param title The title of the notification.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the date of the event associated with the notification.
     *
     * @return The date of the event.
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * Sets the date of the event associated with the notification.
     *
     * @param eventDate The date of the event.
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
