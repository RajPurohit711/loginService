package com.example.loginService.controller;

import com.example.loginService.dto.UserDto;
import com.example.loginService.entity.User;
import com.example.loginService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value="/{id}")
    UserDto getUserById(@PathVariable("id") Long id){
        User user= userService.getUserById(id);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    @RequestMapping(value={"/update"},method = {RequestMethod.POST,RequestMethod.PUT})
    void updateUserDetails(@RequestBody UserDto userDto){
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        userService.save(user);
    }



}
