package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Widget class serves as an abstract superclass for various types of widgets
 * such as tasks, events, and notes within the LifeSync application.
 * It includes common properties like id, title, color, and description.
 */
public abstract class Widget implements Serializable {
    private String id;
    private String title;
    private String color;
    private String description;

    /**
     * Constructs a new Widget with the specified details.
     *
     * @param id          The unique identifier for the widget.
     * @param title       The title of the widget.
     * @param color       The color associated with the widget.
     * @param description The description of the widget.
     */
    public Widget(String id, String title, String color, String description) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.description = description;
    }

    /**
     * Gets the unique identifier for the widget.
     *
     * @return The unique identifier for the widget.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the widget.
     *
     * @param id The unique identifier for the widget.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the title of the widget.
     *
     * @return The title of the widget.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the widget.
     *
     * @param title The title of the widget.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the color associated with the widget.
     *
     * @return The color associated with the widget.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color associated with the widget.
     *
     * @param color The color associated with the widget.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets the description of the widget.
     *
     * @return The description of the widget.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the widget.
     *
     * @param description The description of the widget.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the formatted start time of the widget.
     *
     * @return The formatted start time string.
     */
    public abstract String getFormattedStartTime();

    /**
     * Gets the start time of the widget.
     *
     * @return The start time of the widget.
     */
    public abstract LocalTime getStartTime();

    /**
     * Gets the type of the widget.
     *
     * @return The type of the widget.
     */
    public abstract String getType();

    /**
     * Gets the date of the widget.
     *
     * @return The date of the widget.
     */
    public abstract Date getDate();

    /**
     * Gets the formatted date of the widget.
     *
     * @return The formatted date string.
     */
    public abstract String getFormattedDate();

    /**
     * Gets the guests associated with the widget.
     * By default, returns an empty list for widgets without guests.
     *
     * @return The list of guests.
     */
    public ArrayList<String> getGuests() {
        return new ArrayList<>();
    }

    /**
     * Gets the location of the widget.
     * By default, returns an empty string for widgets without a location.
     *
     * @return The location of the widget.
     */
    public abstract String getLocation();
}
