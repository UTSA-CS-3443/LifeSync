package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private String id;
    private String title;
    private Date eventDate;

    public Notification(String id, String title, Date eventDate) {
        this.id = id;
        this.title = title;
        this.eventDate = eventDate;
    }
}
