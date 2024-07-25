package edu.utsa.cs3443.lifesync.model;

public class Note extends Widget {
    public Note(int id, String title, String description, String color, String type) {
        super(id, title, description, color);
    }

    @Override
    public String getType() {
        return "Note";
    }
}
