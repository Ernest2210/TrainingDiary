package com.example.trainingdiary.models;

import java.sql.Date;

public class User {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Date birthday;
    private boolean isActive;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public boolean isActive() {
        return isActive;
    }
}
