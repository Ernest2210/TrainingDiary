package com.example.trainingdiary.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Comment {
    private int id;
    private int trainingTemplateId;
    private User user;
    private String text;
    private Timestamp date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainingTemplateId() {
        return trainingTemplateId;
    }

    public void setTrainingTemplateId(int trainingTemplateId) {
        this.trainingTemplateId = trainingTemplateId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
