package com.clouds.kafkatest.model;

public class Comment {
    private long id;
    private String name;
    private String text;
    private long rate;
    private long noteId;


    public Comment() {

    }
    public Comment(String name, String text, long rate, long noteId) {
        this.name = name;
        this.text = text;
        this.rate = rate;
        this.noteId = noteId;
    }

    public Comment(long id, String name, String text, long rate, long noteId) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.rate = rate;
        this.noteId = noteId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
}
