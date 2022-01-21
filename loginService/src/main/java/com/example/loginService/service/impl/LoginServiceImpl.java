package com.example.loginService.service.impl;

import com.example.loginService.entity.Login;
import com.example.loginService.repository.LoginRepository;
import com.example.loginService.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginRepository loginRepository;


    public Login getLoginByEmail(String email){
        return loginRepository.findById(email).get();
    }
    public void save(Login login){
        loginRepository.save(login);
    }
    public void deleteLoginByEmail(String Email){
        loginRepository.deleteById(Email);
    }

}
