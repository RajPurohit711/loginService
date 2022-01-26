package com.example.loginService.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class Token {

    @Id
    @GeneratedValue(generator = "seq_gen_alias")
    @GenericGenerator(name="seq_gen_alias",strategy = "increment")
    private Long id;

    private String email;
    private Date date;
    private String token;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
