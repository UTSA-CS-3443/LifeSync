package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;

public class Note extends Widget implements Serializable {
    public Note(String id, String title, String description, String color) {
        super(id, title, color, description);
    }

    @Override
    public String getType() {
        return "Note";
    }
}
