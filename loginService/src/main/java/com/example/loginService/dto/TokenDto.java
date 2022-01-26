package com.example.loginService.dto;

import com.example.loginService.entity.Token;

import java.util.Date;

public class TokenDto {
    String email;
    Date date;
    Token token;

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

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
