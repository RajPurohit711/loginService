package com.example.loginService.controller;

import com.example.loginService.dto.*;
import com.example.loginService.entity.EndUser;
import com.example.loginService.entity.Token;
import com.example.loginService.service.AuthService;
import com.example.loginService.service.UserService;
import net.minidev.json.JSONObject;

import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(value="/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    AuthService authService;

    OtpDto otpDto=new OtpDto();


    @PostMapping(value = "/auth")
    JSONObject auth(@RequestBody TokenDto tokenDto )
    {
        JSONObject jsonObject = new JSONObject();
        Token token = authService.getToken(tokenDto.getEmail());

        System.out.println(token.getToken());

        if(token.getToken().equals(tokenDto.getToken()))
        jsonObject.put("status", 200);

        return jsonObject;
    }

    @RequestMapping(value={"/register/{email}"},method = {RequestMethod.POST,RequestMethod.PUT})
    JSONObject registerUserAndroid(@PathVariable("email") String email){
        JSONObject jsonObject=new JSONObject();
        Random rnd = new Random();
        Long number = Long.valueOf(new Random().nextInt(900000) + 100000);
        otpDto.setOtp(number);
        otpDto.setEmail(email);
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.LoginEmail",otpDto);
        String enOtp=BCrypt.hashpw(number.toString(),BCrypt.gensalt());
        jsonObject.put("enOtp",enOtp);
        return jsonObject;
    }

    @RequestMapping(value={"/register"},method = {RequestMethod.POST,RequestMethod.PUT})
    JSONObject registerUser(@RequestBody ValidateOtpDto validateOtpDto){
        JSONObject jsonObject=new JSONObject();
        Random rnd = new Random();
        Long number = Long.valueOf(new Random().nextInt(900000) + 100000);
        otpDto.setOtp(number);
        String email=validateOtpDto.getEmail();
        otpDto.setEmail(email);
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.LoginEmail",otpDto);
        String enOtp=BCrypt.hashpw(number.toString(),BCrypt.gensalt());
        jsonObject.put("enOtp",enOtp);
        return jsonObject;
    }

    @RequestMapping(value={"/verify"},method = {RequestMethod.POST,RequestMethod.PUT})
    JSONObject verifyAndRegister(@RequestBody ValidateOtpDto validateOtpDto){

        JSONObject jsonObject=new JSONObject();
        if (BCrypt.checkpw(String.valueOf(validateOtpDto.getOtp()),validateOtpDto.getEnOtp())){
            EndUser endUser =new EndUser();
            String password=validateOtpDto.getPassword();
            String enPassword=BCrypt.hashpw(password,BCrypt.gensalt());
            validateOtpDto.setPassword(enPassword);
            BeanUtils.copyProperties(validateOtpDto,endUser);
            endUser.setUserSince(java.time.LocalDate.now());
            userService.save(endUser);


            Token token = authService.getToken(validateOtpDto.getEmail());

            jsonObject.put("token",token);
            jsonObject.put("status",201);
            return jsonObject;
        }
        jsonObject.put("status",500);
        return jsonObject;

    }


    @RequestMapping(value={"/login"},method = {RequestMethod.POST,RequestMethod.PUT})
    JSONObject authenticateUser(@RequestBody LoginDto loginDto) {
        JSONObject jsonObject=new JSONObject();
        if(userService.exists(loginDto))
        {
            jsonObject.put("status",201);
            EndUser user = userService.getUserByEmail(loginDto.getEmail());
            jsonObject.put("id",user.getId());
            return jsonObject;
        }
        jsonObject.put("status",500);
        return jsonObject;

    }


}


