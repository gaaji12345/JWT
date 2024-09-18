package com.example.JWTGaaji.dto;/*  gaajiCode
    99
    18/09/2024
    */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    private String username;
    private String password;
}
