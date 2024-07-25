package edu.utsa.cs3443.lifesync.model;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private String biography;
    private String gender; // Assuming "Male" or "Female" as string values
    private ArrayList<Widget> widgets;
    private ArrayList<Notification> notifications;

    // Constructor
    public User(int id, String name, String email, String biography, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.biography = biography;
        this.gender = gender;
        this.notifications = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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



    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
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
    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    public void removeNotification(Notification notification) {
        this.notifications.remove(notification);
    }
}
