package com.example.loginService.dto;

import com.example.loginService.entity.Token;

import java.util.Date;

public class TokenDto {
    String email;
    Date date;
    String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
