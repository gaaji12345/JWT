package com.example.JWTGaaji.dto;/*  gaajiCode
    99
    18/09/2024
    */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AuthResponceDTO {

    private String accessToken;
    private String tokenType ="Bearer ";

    public AuthResponceDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
