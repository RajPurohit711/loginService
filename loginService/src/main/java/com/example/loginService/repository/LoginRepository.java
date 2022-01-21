package com.example.loginService.repository;

import com.example.loginService.entity.Login;
import com.example.loginService.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login,String> {
}
