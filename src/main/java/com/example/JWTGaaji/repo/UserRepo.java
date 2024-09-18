package com.example.JWTGaaji.repo;/*  gaajiCode
    99
    18/09/2024
    */

import com.example.JWTGaaji.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserE,Integer> {


    Optional<UserE> findByUsername(String name);
    Boolean existsByUsername(String username);
}
