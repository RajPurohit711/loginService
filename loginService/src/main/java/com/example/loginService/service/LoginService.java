package com.example.loginService.service;

import com.example.loginService.entity.Login;

public interface LoginService {

    public Login getLoginByEmail(String email);
    public void save(Login login);
    public void deleteLoginByEmail(String Email);
}
