package com.example.loginService.service;

import com.example.loginService.entity.Login;
import com.example.loginService.entity.User;

public interface LoginService {

    public Login getLoginByEmail(String email);

}
