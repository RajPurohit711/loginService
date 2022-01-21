package com.example.loginService.service;

import com.example.loginService.entity.EndUser;

public interface UserService {
    public EndUser getUserById(Long id);
    public void save(EndUser endUser);
    public EndUser getUserByEmail(String email);
    public void deleteUserById(Long id);
}
