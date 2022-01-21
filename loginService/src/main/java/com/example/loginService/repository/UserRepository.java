package com.example.loginService.repository;

import com.example.loginService.entity.EndUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<EndUser, Long> {
    EndUser findByEmail(String email);


}
