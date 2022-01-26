package com.example.loginService.repository;

import com.example.loginService.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token,Long> {

    Token findByEmail(String email);

}
