package edu.utsa.cs3443.lifesync.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Note extends Widget implements Serializable {
    private Date noteDate;
    public Note(String id, String title, String description, String color) {
        super(id, title, color, description);
        this.noteDate = new Date();
    }

    @Override
    public String getType() {
        return "Note";
    }
    @Override
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(noteDate);
    }
    @Override
    public Date getDate() {
        return noteDate;
    }
    public void setDate(Date noteDate) {
        this.noteDate = noteDate;
    }
    @Override
    public String getFormattedStartTime(){
        return "";
    }
    public LocalTime getStartTime(){
        return  LocalTime.parse("23:59");
    }
}
