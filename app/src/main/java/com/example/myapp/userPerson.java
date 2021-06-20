package com.example.myapp;

public class userPerson {
    String phone_number;
    String name;
    String provider;
    String userId;
    String email;
    public userPerson(String phone_number, String name, String provider, String userId, String email) {
        this.phone_number = phone_number;
        this.name = name;
        this.provider = provider;
        this.userId = userId;
        this.email = email;
    }
    public userPerson() {
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
