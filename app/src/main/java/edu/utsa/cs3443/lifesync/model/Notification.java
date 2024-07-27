package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private String id;
    private String title;
    private String eventDate;

    public Notification(String id, String title, String eventDate) {
        this.id = id;
        this.title = title;
        this.eventDate = eventDate;
    }
}
