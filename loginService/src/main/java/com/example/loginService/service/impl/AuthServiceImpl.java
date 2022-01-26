package com.example.loginService.service.impl;

import com.example.loginService.entity.Token;
import com.example.loginService.repository.TokenRepository;
import com.example.loginService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Token getToken(String email) {
        Token token = tokenRepository.findByEmail(email);
        if (token == null) return token;
        token = new Token(email);
        return token;
    }


}
