package edu.utsa.cs3443.lifesync.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The User class represents a user within the LifeSync application.
 * It includes details such as the user's name, email, biography, gender, and a list of widgets.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String biography;
    private String gender; // Assuming "Male" or "Female" as string values
    private ArrayList<Widget> widgets;
    private int taskNum = 0;
    private int noteNum = 0;
    private int eventNum = 0;

    /**
     * Constructs a new User with the specified details.
     *
     * @param name      The name of the user.
     * @param email     The email of the user.
     * @param biography The biography of the user.
     * @param gender    The gender of the user.
     */
    public User(String name, String email, String biography, String gender) {
        this.name = name;
        this.email = email;
        this.biography = biography;
        this.gender = gender;
        this.widgets = new ArrayList<>();
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the user.
     *
     * @return The unique identifier for the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The unique identifier for the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the biography of the user.
     *
     * @return The biography of the user.
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Sets the biography of the user.
     *
     * @param biography The biography of the user.
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender of the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the list of widgets associated with the user.
     *
     * @return The list of widgets.
     */
    public ArrayList<Widget> getWidgets() {
        return widgets;
    }

    /**
     * Sets the list of widgets associated with the user.
     *
     * @param widgets The list of widgets.
     */
    public void setWidgets(ArrayList<Widget> widgets) {
        this.widgets = widgets;
    }

    /**
     * Loads widgets from a CSV file located in the assets folder.
     *
     * @param activity The activity context used to access assets.
     */
    public void loadWidget(Activity activity) {
        AssetManager manager = activity.getAssets();
        String fileName = "widget.csv";
        try {
            // Open the CSV file from the assets folder
            InputStream file = manager.open(fileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String widgetType = scan.nextLine().trim();
                if (widgetType.isEmpty()) {
                    break;
                }
                String title;
                String color;
                String description;
                String address;
                String guests;
                String eventDate;
                String reminderTimeBefore;
                boolean repeatDay;
                String repeatTime;
                String startTime;
                String taskDate;

                switch (widgetType) {
                    case "Event":
                        title = scan.nextLine().trim();
                        color = scan.nextLine().trim();
                        description = scan.nextLine().trim();
                        address = scan.nextLine().trim();
                        guests = scan.nextLine().trim();
                        eventDate = scan.nextLine().trim();
                        reminderTimeBefore = scan.nextLine().trim();
                        startTime = scan.nextLine().trim();
                        this.createNewEvent(title, color, description, address, guests, eventDate, reminderTimeBefore, startTime);
                        break;

                    case "Task":
                        title = scan.nextLine().trim();
                        color = scan.nextLine().trim();
                        description = scan.nextLine().trim();
                        taskDate = scan.nextLine().trim();
                        reminderTimeBefore = scan.nextLine().trim();
                        repeatDay = Boolean.parseBoolean(scan.nextLine().trim());
                        if (repeatDay) {
                            repeatTime = scan.nextLine().trim();
                            startTime = scan.nextLine().trim();
                            this.createNewTask(title, color, description, taskDate, reminderTimeBefore, repeatTime, startTime);
                        } else {
                            startTime = scan.nextLine().trim();
                            this.createNewTask(title, color, description, taskDate, reminderTimeBefore, "", startTime);
                        }
                        break;

                    case "Note":
                        title = scan.nextLine().trim();
                        color = scan.nextLine().trim();
                        description = scan.nextLine().trim();
                        this.createNewNote(title, color, description);
                        break;
                }

                // Skip the blank line if exists
                if (scan.hasNextLine()) {
                    scan.nextLine();
                }
            }
            scan.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(activity, "Error loading widget: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Generates a unique identifier for a widget of the specified type.
     *
     * @param type The type of the widget (Event, Task, Note).
     * @return The generated unique identifier.
     */
    public String IDGenerator(String type) {
        String id;
        switch (type) {
            case "Event":
                eventNum += 1;
                id = String.format("%s%03d", type.substring(0, 3).toUpperCase(), eventNum);
                return id;
            case "Task":
                taskNum += 1;
                id = String.format("%s%03d", type.substring(0, 3).toUpperCase(), taskNum);
                return id;
            case "Note":
                noteNum += 1;
                id = String.format("%s%03d", type.substring(0, 3).toUpperCase(), noteNum);
                return id;
        }
        return null;
    }

    /**
     * Adds a widget to the user's list of widgets.
     *
     * @param widget The widget to be added.
     */
    public void addWidget(Widget widget) {
        this.widgets.add(widget);
    }

    /**
     * Creates a new task with the specified details and adds it to the user's list of widgets.
     *
     * @param title              The title of the task.
     * @param color              The color associated with the task.
     * @param description        The description of the task.
     * @param taskDate           The date of the task.
     * @param reminderTimeBefore The reminder time before the task.
     * @param repeatDate         The repeat dates for the task.
     * @param startTime          The start time of the task.
     */
    public void createNewTask(String title, String color, String description, String taskDate, String reminderTimeBefore, String repeatDate, String startTime) {
        LocalTime reminderTimeConverted = LocalTime.parse(reminderTimeBefore);
        LocalTime startTimeConverted = LocalTime.parse(startTime);
        Date taskDateConverted = new Date();
        String taskId = IDGenerator("Task");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            taskDateConverted = dateFormat.parse(taskDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (color.isEmpty()) {
            color = "black";
        }
        if (repeatDate.isEmpty()) {
            Task task = new Task(taskId, title, color, description, taskDateConverted, reminderTimeConverted, startTimeConverted);
            this.addWidget(task);
        } else {
            String[] repeatDates = repeatDate.split(",");
            ArrayList<Date> repeatDateList = new ArrayList<>();
            for (String repeatDateItem : repeatDates) {
                try {
                    repeatDateList.add(dateFormat.parse(repeatDateItem.trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Set<Date> set = new HashSet<>(repeatDateList);
            repeatDateList.clear();
            repeatDateList.addAll(set);
            Task task = new Task(taskId, title, color, description, taskDateConverted, reminderTimeConverted, startTimeConverted);
            this.addWidget(task);
            for (Date date : repeatDateList) {
                if (!date.equals(taskDateConverted)) {
                    taskId = IDGenerator("Task");
                    Task repeatedTask = new Task(taskId, title, color, description, date, reminderTimeConverted, startTimeConverted);
                    this.addWidget(repeatedTask);
                }
            }
        }
    }

    /**
     * Creates a new event with the specified details and adds it to the user's list of widgets.
     *
     * @param title              The title of the event.
     * @param color              The color associated with the event.
     * @param description        The description of the event.
     * @param address            The address of the event.
     * @param guests             The guests attending the event.
     * @param eventDate          The date of the event.
     * @param reminderTimeBefore The reminder time before the event.
     * @param startTime          The start time of the event.
     */
    public void createNewEvent(String title, String color, String description, String address, String guests, String eventDate, String reminderTimeBefore, String startTime) {
        LocalTime reminderTimeConverted = LocalTime.parse(reminderTimeBefore);
        LocalTime startTimeConverted = LocalTime.parse(startTime);
        String eventId = IDGenerator("Event");
        Date eventDateConverted = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String[] guestArray = guests.split(",");
        ArrayList<String> guestsList = new ArrayList<>();
        for (String guest : guestArray) {
            guestsList.add(guest.trim());
        }
        try {
            eventDateConverted = dateFormat.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (color.isEmpty()) {
            color = "black";
        }
        Event event = new Event(eventId, title, color, description, address, guestsList, eventDateConverted, reminderTimeConverted, startTimeConverted);
        this.addWidget(event);
    }

    /**
     * Creates a new note with the specified details and adds it to the user's list of widgets.
     *
     * @param title       The title of the note.
     * @param color       The color associated with the note.
     * @param description The description of the note.
     */
    public void createNewNote(String title, String color, String description) {
        String noteId = IDGenerator("Note");
        if (color.isEmpty()) {
            color = "black";
        }
        Note note = new Note(noteId, title, description, color);
        this.addWidget(note);
    }

    /**
     * Gets the number of widgets associated with the user.
     *
     * @return The number of widgets.
     */
    public int getNumberOfWidget() {
        return this.widgets.size();
    }

    /**
     * Removes a widget from the user's list of widgets.
     *
     * @param widget The widget to be removed.
     */
    public void removeWidget(Widget widget) {
        this.widgets.remove(widget);
    }

    /**
     * Removes a widget from the user's list of widgets by its unique identifier.
     *
     * @param id The unique identifier of the widget to be removed.
     */
    public void removeWidgetById(String id) {
        for (Widget widget : this.widgets) {
            if (id.equals(widget.getId())) {
                removeWidget(widget);
                break;
            }
        }
    }

    /**
     * Sorts the user's list of widgets by date and time.
     */
    public void sortWidgetsByDateTime() {
        Collections.sort(this.widgets, new Comparator<Widget>() {
            @Override
            public int compare(Widget widget1, Widget widget2) {
                int dateComparison = widget1.getDate().compareTo(widget2.getDate());
                if (dateComparison != 0) {
                    return dateComparison;
                } else {
                    return widget1.getStartTime().compareTo(widget2.getStartTime());
                }
            }
        });
    }
}
