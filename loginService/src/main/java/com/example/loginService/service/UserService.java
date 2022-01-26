package com.example.loginService.service;

import com.example.loginService.dto.LoginDto;
import com.example.loginService.entity.EndUser;

public interface UserService {
    EndUser getUserById(Long id);
    void save(EndUser endUser);
    EndUser getUserByEmail(String email);
    void deleteUserById(Long id);
    void deleteUserByEmail(String  email);
    boolean exists(LoginDto loginDto);
}
