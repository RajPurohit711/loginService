package com.example.loginService.service.impl;

import com.example.loginService.entity.EndUser;
import com.example.loginService.repository.UserRepository;
import com.example.loginService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public EndUser getUserById(Long id){
        return userRepository.findById(id).get();
    }
    public void save(EndUser endUser){
        userRepository.save(endUser);
    }

    public EndUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
    }

