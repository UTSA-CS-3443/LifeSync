package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * The Note class represents a note within the LifeSync application.
 * It includes details such as the note date, title, description, and color.
 */
public class Note extends Widget implements Serializable {
    private Date noteDate;

    /**
     * Constructs a new Note with the specified details.
     *
     * @param id          The unique identifier for the note.
     * @param title       The title of the note.
     * @param description The description of the note.
     * @param color       The color associated with the note.
     */
    public Note(String id, String title, String description, String color) {
        super(id, title, color, description);
        this.noteDate = new Date();
    }

    /**
     * Gets the type of the widget, which is "Note".
     *
     * @return The type of the widget.
     */
    @Override
    public String getType() {
        return "Note";
    }

    /**
     * Gets the formatted date of the note.
     *
     * @return The formatted date string.
     */
    @Override
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(noteDate);
    }

    /**
     * Gets the location of the note.
     *
     * @return An empty string as notes do not have a location.
     */
    @Override
    public String getLocation() {
        return "";
    }

    /**
     * Gets the date of the note.
     *
     * @return The date of the note.
     */
    @Override
    public Date getDate() {
        return noteDate;
    }

    /**
     * Sets the date of the note.
     *
     * @param noteDate The date of the note.
     */
    public void setDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    /**
     * Gets the formatted start time of the note.
     *
     * @return An empty string as notes do not have a start time.
     */
    @Override
    public String getFormattedStartTime() {
        return "";
    }

    /**
     * Gets the start time of the note.
     *
     * @return A default time of 23:59 as notes do not have a start time.
     */
    public LocalTime getStartTime() {
        return LocalTime.parse("23:59");
    }
}
