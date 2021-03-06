package com.example.loginService.service.impl;

import com.example.loginService.dto.LoginDto;
import com.example.loginService.entity.EndUser;
import com.example.loginService.repository.TokenRepository;
import com.example.loginService.repository.UserRepository;
import com.example.loginService.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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

    public void deleteUserByEmail(String  email){
        userRepository.deleteByEmail(email);
    }

    public boolean exists(LoginDto loginDto){
        EndUser endUser=userRepository.findByEmail(loginDto.getEmail());
        if (BCrypt.checkpw(loginDto.getPassword(),endUser.getPassword())){
            return true;
        }
        return false;
    }


    }

