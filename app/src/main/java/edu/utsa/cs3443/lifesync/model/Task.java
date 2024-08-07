package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * The Task class represents a task within the LifeSync application.
 * It includes details such as the task date, reminder time, and start time.
 */
public class Task extends Widget implements Serializable {
    private Date taskDate;
    private LocalTime reminderTimeBefore;
    private LocalTime startTime;

    /**
     * Constructs a new Task with the specified details.
     *
     * @param id                The unique identifier for the task.
     * @param title             The title of the task.
     * @param color             The color associated with the task.
     * @param description       The description of the task.
     * @param taskDate          The date of the task.
     * @param reminderTimeBefore The reminder time before the task.
     * @param startTime         The start time of the task.
     */
    public Task(String id, String title, String color, String description, Date taskDate, LocalTime reminderTimeBefore, LocalTime startTime) {
        super(id, title, color, description);
        this.taskDate = taskDate;
        this.reminderTimeBefore = reminderTimeBefore;
        this.startTime = startTime;
    }

    /**
     * Gets the formatted date of the task.
     *
     * @return The formatted date string.
     */
    @Override
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(taskDate);
    }

    /**
     * Gets the location of the task.
     *
     * @return An empty string as tasks do not have a location.
     */
    @Override
    public String getLocation() {
        return "";
    }

    /**
     * Gets the date of the task.
     *
     * @return The date of the task.
     */
    @Override
    public Date getDate() {
        return taskDate;
    }

    /**
     * Sets the date of the task.
     *
     * @param taskDate The date of the task.
     */
    public void setDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    /**
     * Gets the formatted start time of the task.
     *
     * @return The formatted start time string.
     */
    @Override
    public String getFormattedStartTime() {
        return startTime.toString();
    }

    /**
     * Gets the start time of the task.
     *
     * @return The start time of the task.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the task.
     *
     * @param startTime The start time of the task.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the type of the widget, which is "Task".
     *
     * @return The type of the widget.
     */
    @Override
    public String getType() {
        return "Task";
    }
}
