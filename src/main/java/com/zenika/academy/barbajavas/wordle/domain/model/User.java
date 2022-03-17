package com.zenika.academy.barbajavas.wordle.domain.model;

public class User {
    private String userTid;
    private String name;
    private String email;


    public User(String userTid, String name, String email) {
        this.userTid = userTid;
        this.name = name;
        this.email = email;
    }

    public String getUserTid() {
        return userTid;
    }

    public void setUserTid(String userTid) {
        this.userTid = userTid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
