package com.example.loginService.controller;

import com.example.loginService.dto.RegisterDto;
import com.example.loginService.dto.UserDto;
import com.example.loginService.entity.EndUser;
import com.example.loginService.entity.Login;
import com.example.loginService.service.LoginService;
import com.example.loginService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @GetMapping(value="/{id}")
    UserDto getUserById(@PathVariable("id") Long id){
        EndUser endUser = userService.getUserById(id);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(endUser,userDto);
        return userDto;
    }

    @RequestMapping(value={"/update"},method = {RequestMethod.POST,RequestMethod.PUT})
    void updateUserDetails(@RequestBody UserDto userDto){
        EndUser enduser=new EndUser();
        BeanUtils.copyProperties(userDto,enduser);
        userService.save(enduser);
    }

    @RequestMapping(value={"/register"},method = {RequestMethod.POST,RequestMethod.PUT})
    void registerUser(@RequestBody RegisterDto registerDto){
        EndUser endUser =new EndUser();
        Login login=new Login();
        BeanUtils.copyProperties(registerDto,endUser);
        BeanUtils.copyProperties(registerDto,login);
        System.out.println(java.time.LocalDate.now());
        endUser.setUserSince(java.time.LocalDate.now());

        userService.save(endUser);
        loginService.save(login);
    }
    @GetMapping(value="/email/{email}")
    UserDto getUserByEmail(@PathVariable("email") String email){
        EndUser endUser = userService.getUserByEmail(email);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(endUser,userDto);
        return userDto;
    }

    @DeleteMapping(value="/{id}")
    void deleteUserById(@PathVariable("id") Long id){
        EndUser endUser=userService.getUserById(id);
        String email=endUser.getEmail();
        userService.deleteUserById(id);
        loginService.deleteLoginByEmail(email);
    }

}
