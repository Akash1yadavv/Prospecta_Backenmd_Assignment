package com.prospecta.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserDto {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password is mandatory")
    private String password;


}