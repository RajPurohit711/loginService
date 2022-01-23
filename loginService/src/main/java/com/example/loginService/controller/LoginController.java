package com.example.loginService.controller;

import com.example.loginService.dto.LoginDto;
import com.example.loginService.dto.UpdatePasswordDto;
import com.example.loginService.entity.EndUser;
import com.example.loginService.service.UserService;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    UserService userService;


    @RequestMapping(value={"/update"},method = {RequestMethod.POST,RequestMethod.PUT})
    JSONObject updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto){
        JSONObject jsonObject=new JSONObject();
        EndUser endUser= userService.getUserByEmail(updatePasswordDto.getEmail());
        if (endUser.getPassword().equals(updatePasswordDto.getOldPassword())){
            if(updatePasswordDto.getNewPassword().equals(updatePasswordDto.getConfirmNewPassword())){
                endUser.setPassword(updatePasswordDto.getNewPassword());
                jsonObject.put("status",201);
                return jsonObject;
                }
                }
        jsonObject.put("status",500);
        return jsonObject;

    }


    @RequestMapping(value={"/login"},method = {RequestMethod.POST,RequestMethod.PUT})
    JSONObject authenticateUser(@RequestBody LoginDto loginDto){
        JSONObject jsonObject=new JSONObject();
        if(userService.exists(loginDto))
        {
            jsonObject.put("status",201);
            return jsonObject;
        }
        jsonObject.put("status",500);
        return jsonObject;
    }


}


//
//    @GetMapping(value="/{email}")
//    String getPasswordByEmail(@PathVariable("email") String  email){
//        EndUser endUser=new EndUser();
//        endUser=userService.getUserByEmail(email);
//        String password=endUser.getPassword();
//        return password;
//    }



//    @RequestMapping(value={"/update"},method = {RequestMethod.POST,RequestMethod.PUT})
//    void updatePassword(@RequestBody UserDto userDto){
//
//        EndUser endUser=userService.getUserByEmail(userDto.getEmail());
//        BeanUtils.copyProperties(userDto,endUser);
//        userService.save(endUser);
//    }
