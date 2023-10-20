package com.example.trainingdiary.models;

public class ExerciseType {
    private int id;
    private String title;
    private String photoPath;
    private String description;
    private String technique;
    private ExerciseComplexity complexity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public ExerciseComplexity getComplexity() {
        return complexity;
    }

    public void setComplexity(ExerciseComplexity complexity) {
        this.complexity = complexity;
    }
}
