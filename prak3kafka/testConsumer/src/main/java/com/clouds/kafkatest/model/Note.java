package com.clouds.kafkatest.model;

public class Note{
    private long id;
    private String name;
    private String text;


    public Note(){

    }

    public Note(long id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Note(String name, String text) {
        this.name = name;
        this.text = text;
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



    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
