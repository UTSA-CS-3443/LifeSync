package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public abstract class Widget implements Serializable {
    private String id;
    private String title;
    private String color;
    private String description;

    public Widget(String id, String title, String color, String description) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getFormattedStartTime();
    public abstract LocalTime getStartTime();
    public abstract String getType();
    public abstract Date getDate();
    public abstract String getFormattedDate();
    public ArrayList<String> getGuests(){
        return new ArrayList<String>();
    }
    public abstract String getLocation();
}

