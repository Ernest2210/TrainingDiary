package com.example.trainingdiary.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BodyParam  {
    public static final String WEIGHT = "Weight";
    public static final String HEIGHT = "Height";
    public static final String BREAST = "Breast";
    public static final String HIP = "Hip";
    public static final String PELVIS = "Pelvis";
    public static final String SHIN = "Shin";
    public static final String SHOULDERS = "Shoulders";
    public static final String WAIST = "Waist";
    public static final String WRIST = "Wrist";

    public static final String FOREARM = "Forearm";

    public static final String[] TYPES = new String[]{WEIGHT, HEIGHT, BREAST, HIP,
            PELVIS, SHIN, SHOULDERS, WAIST, WRIST, FOREARM};

    private String type;
    private int id;
    private int userId;
    private Date date;
    private double value;

    public BodyParam(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
