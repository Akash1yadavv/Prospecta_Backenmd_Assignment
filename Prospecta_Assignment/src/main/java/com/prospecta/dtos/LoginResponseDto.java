package com.prospecta.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {
	
    private String token;
    private long expiresIn;

}