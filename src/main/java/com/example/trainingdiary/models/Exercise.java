package com.example.trainingdiary.models;

import java.sql.Date;
import java.util.List;

public class Exercise{
    private int id;
    private int userId;
    private ExerciseType exerciseType;
    private Date date;
    private String comment;
    private int difficult;
    private List<Approach> approaches;

    public List<Approach> getApproaches() {
        return approaches;
    }

    public void setApproaches(List<Approach> approaches) {
        this.approaches = approaches;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }
}
