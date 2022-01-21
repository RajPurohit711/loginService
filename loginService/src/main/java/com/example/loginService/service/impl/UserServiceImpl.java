package com.example.loginService.service.impl;

import com.example.loginService.entity.User;
import com.example.loginService.repository.UserRepository;
import com.example.loginService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }
    public void save(User user){
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    }

