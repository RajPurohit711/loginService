package com.example.loginService.service;

import com.example.loginService.entity.User;

public interface UserService {
    public User getUserById(Long id);
    public void save(User user);
    public User getUserByEmail(String email);

}
