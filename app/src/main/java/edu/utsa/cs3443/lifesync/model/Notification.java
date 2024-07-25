package edu.utsa.cs3443.lifesync.model;

public class Notification {
    private String id;
    private String title;
    private String eventDate;

    public Notification(String id, String title, String eventDate) {
        this.id = id;
        this.title = title;
        this.eventDate = eventDate;
    }
}
