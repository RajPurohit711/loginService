package com.example.loginService.service;

import com.example.loginService.entity.Token;

public interface AuthService {

   Token getToken(String email);
}
