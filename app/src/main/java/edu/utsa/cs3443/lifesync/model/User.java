package edu.utsa.cs3443.lifesync.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User {
    private String id;
    private String name;
    private String email;
    private String biography;
    private String gender; // Assuming "Male" or "Female" as string values
    private ArrayList<Widget> widgets;
    private int taskNum = 0;
    private int noteNum = 0;
    private int eventNum = 0;


    // Constructor
    public User( String name, String email, String biography, String gender) {
        this.name = name;
        this.email = email;
        this.biography = biography;
        this.gender = gender;
        this.widgets = new ArrayList<Widget>();
        this.taskNum = 0;
        this.noteNum = 0;
        this.eventNum = 0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void loadWidget(Activity activity) {
        AssetManager manager = activity.getAssets();
        String fileName = "AccountInfo.csv";
        try {
            // Open the CSV file from the assets folder
            InputStream file = manager.open(fileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String widgetType = scan.nextLine().trim();
                if (widgetType.isEmpty()) {
                    break;
                }
                String generatedID;
                String title;
                String color;
                String description;
                String address;
                ArrayList<String> guests;
                String eventDate;
                String reminderTimeBefore;
                String repeatDay;
                LocalTime startTime;
                String taskDate;

                switch (widgetType) {
                    case "Event":
                        generatedID =  IDGenerator(widgetType);
                        title = scan.nextLine().trim();
                        color = scan.nextLine().trim();
                        description = scan.nextLine().trim();
                        address = scan.nextLine().trim();
                        guests = new ArrayList<>(Arrays.asList(scan.nextLine().trim().split(", ")));
                        eventDate = scan.nextLine().trim();
                        reminderTimeBefore = scan.nextLine().trim();
                        startTime = LocalTime.parse(scan.nextLine().trim());


                        Event event = new Event(generatedID,title, color, description, address, guests, eventDate, reminderTimeBefore, startTime);
                        this.widgets.add(event);
                        break;

                    case "Task":
                        generatedID =  IDGenerator(widgetType);
                        title = scan.nextLine().trim();
                        color = scan.nextLine().trim();
                        description = scan.nextLine().trim();
                        taskDate = scan.nextLine().trim();
                        reminderTimeBefore = scan.nextLine().trim();
                        repeatDay = scan.nextLine().trim();
                        startTime = LocalTime.parse(scan.nextLine().trim());

                        Task task = new Task(generatedID, title, color, description, taskDate, reminderTimeBefore, repeatDay, startTime);
                        this.widgets.add(task);
                        break;

                    case "Note":
                        generatedID =  IDGenerator(widgetType);
                        title = scan.nextLine().trim();
                        color = scan.nextLine().trim();
                        description = scan.nextLine().trim();

                        Note note = new Note(generatedID,title, color, description);
                        this.widgets.add(note);
                        break;
                }

                // Skip the blank line if exists
                if (scan.hasNextLine()) {
                    scan.nextLine();
                }
                Toast.makeText(activity, "Loading widget successful", Toast.LENGTH_LONG).show();

            }
            scan.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle error accessing assets
            Toast.makeText(activity, "Error loading widget " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public String IDGenerator(String type){
        String id;
        switch (type) {
            case "Event":
                eventNum +=1;
                id = String.format("%s%03d", type.substring(0, 3).toUpperCase(), eventNum);
                return id;
            case "Task":
                taskNum +=1;
                id = String.format("%s%03d", type.substring(0, 3).toUpperCase(), taskNum);
                return id;
            case "Note":
                noteNum +=1;
                id = String.format("%s%03d", type.substring(0, 3).toUpperCase(), noteNum);
                return id;
        }
        return null;
    }
    // Methods to manage events
    public void addWidget(Widget widget) {
        this.widgets.add(widget);
    }


    public void removeWidget(Widget widget) {
        this.widgets.remove(widget);
    }
    public void removeWidgetById(String id, Activity activity) {
        for(Widget widget: this.widgets){
            if(id.equals(widget.getId())){
                removeWidget(widget);
                Toast.makeText(activity, "Removed " + widget.getType() +id, Toast.LENGTH_LONG).show();
            }
        }
        Toast.makeText(activity, "Widget with ID " + id + " not found", Toast.LENGTH_LONG).show();
    }

    // Methods to manage notifications

}
