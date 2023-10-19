package com.example.trainingdiary.models;

import java.util.UUID;

public class Remember {
    private String uuid;
    private int userId;
    public Remember(int userId){
        this.userId = userId;
        this.uuid = UUID.randomUUID().toString();
    }

    public Remember(){}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }


}
