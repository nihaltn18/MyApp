package com.example.myapp;

public class message {
    String message;
    String from_id;
    String from_name;
    String to_id;
    boolean Private;
    boolean anonymous;
    String to_name;

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public message(String message, String from_id, String to_id, boolean aPrivate, boolean anonymous, String from_name,String to_name) {
        this.message = message;
        this.to_name = to_name;
        this.from_name=from_name;
        this.from_id = from_id;
        this.to_id = to_id;
        Private = aPrivate;
        this.anonymous = anonymous;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public boolean isPrivate() {
        return Private;
    }

    public void setPrivate(boolean aPrivate) {
        Private = aPrivate;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
