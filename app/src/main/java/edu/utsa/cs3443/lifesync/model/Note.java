package edu.utsa.cs3443.lifesync.model;

public class Note extends Widget {
    public Note(String id, String title, String description, String color) {
        super(id, title, color, description);
    }

    @Override
    public String getType() {
        return "Note";
    }
}
