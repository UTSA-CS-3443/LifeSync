package edu.utsa.cs3443.lifesync.model;

import java.util.ArrayList;

public class Task implements Journal{
    private String name;
    private ArrayList<String> guests;
    private String description;
    private String time;
    private String date;
    private String location;
    private boolean repeat;
    private String repeatTime;

    public Task(String name, String getName, String location, String date, String time, String description, ArrayList<String> guests) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.description = description;
        this.guests = guests;
    }

    public Task(String name, String getName, String location, String date, String time, String description, ArrayList<String> guests, boolean repeat ,String repeatTime) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.description = description;
        this.guests = guests;
        this.repeat = repeat;
        this.repeatTime = repeatTime;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<String> guests) {
        this.guests = guests;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
