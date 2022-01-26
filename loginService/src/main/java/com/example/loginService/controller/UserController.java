package com.example.loginService.controller;

import com.example.loginService.dto.LoginDto;
import com.example.loginService.dto.RegisterDto;
import com.example.loginService.dto.UserDto;
import com.example.loginService.entity.EndUser;
import com.example.loginService.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    UserService userService;



    @GetMapping(value="/{id}")
    UserDto getUserById(@PathVariable("id") Long id){
        EndUser endUser = userService.getUserById(id);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(endUser,userDto);
        return userDto;
    }

    @RequestMapping(value={"/update"},method = {RequestMethod.POST,RequestMethod.PUT})
    JSONObject updateUserDetails(@RequestBody UserDto userDto){
        JSONObject jsonObject=new JSONObject();
        EndUser endUser=userService.getUserByEmail(userDto.getEmail());
        BeanUtils.copyProperties(userDto,endUser);
        userService.save(endUser);
        jsonObject.put("status",201);
        return jsonObject;
    }

    @GetMapping(value="/email/{email}")
    UserDto getUserByEmail(@PathVariable("email") String email){
        EndUser endUser = userService.getUserByEmail(email);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(endUser,userDto);
        return userDto;
    }



    @DeleteMapping(value="/delete")
    JSONObject deleteUserByEmail(@RequestBody LoginDto loginDto){
        JSONObject jsonObject=new JSONObject();
        if(userService.exists(loginDto))
        {
            userService.deleteUserByEmail(loginDto.getEmail());
            jsonObject.put("status",201);
            return jsonObject;
        }
        jsonObject.put("status",500);
        return jsonObject;
    }

}


//    @DeleteMapping(value="/{id}")
//    void deleteUserById(@PathVariable("id") Long id){
//        EndUser endUser=userService.getUserById(id);
//        String email=endUser.getEmail();
//        userService.deleteUserById(id);
//    }

//    @RequestMapping(value={"/login"},method = {RequestMethod.POST,RequestMethod.PUT})
//    JSONObject authenticateUser(@RequestBody LoginDto loginDto){
//        JSONObject jsonObject=new JSONObject();
//        if(userService.exists(loginDto))
//        {
//            jsonObject.put("status",201);
//            return jsonObject;
//        }
//        jsonObject.put("status",500);
//        return jsonObject;
//    }


//    @RequestMapping(value={"/update"},method = {RequestMethod.POST,RequestMethod.PUT})
//    JSONObject updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto){
//        JSONObject jsonObject=new JSONObject();
//        EndUser endUser= userService.getUserByEmail(updatePasswordDto.getEmail());
//        if (endUser.getPassword().equals(updatePasswordDto.getOldPassword())){
//            if(updatePasswordDto.getNewPassword().equals(updatePasswordDto.getConfirmNewPassword())){
//                endUser.setPassword(updatePasswordDto.getNewPassword());
//                jsonObject.put("status",201);
//                return jsonObject;
//            }
//        }
//        jsonObject.put("status",500);
//        return jsonObject;
//
//    }
