package com.example.loginService.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public class Token {

    @Id
    private String email;
    private Date date;
    private String token;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Token(String email) {
        this.email = email;
        this.token = UUID.randomUUID().toString();
        this.date = new Date();
    }
}
