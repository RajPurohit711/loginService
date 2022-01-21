package com.example.loginService.controller;

import com.example.loginService.dto.LoginDto;
import com.example.loginService.entity.Login;
import com.example.loginService.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping(value="/{email}")
    String getPasswordByEmail(@PathVariable("email") String  email){
        Login login=new Login();
        login=loginService.getLoginByEmail(email);
        String password=login.getPassword();
        return password;
    }
    @RequestMapping(value={"/update"},method = {RequestMethod.POST,RequestMethod.PUT})
    void updatePasswordByEmail(@RequestBody LoginDto loginDto){
        Login login=new Login();
        BeanUtils.copyProperties(loginDto,login);
        loginService.save(login);
    }

}
