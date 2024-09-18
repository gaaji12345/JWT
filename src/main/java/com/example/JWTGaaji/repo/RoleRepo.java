package com.example.JWTGaaji.repo;/*  gaajiCode
    99
    18/09/2024
    */

import com.example.JWTGaaji.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);
}
