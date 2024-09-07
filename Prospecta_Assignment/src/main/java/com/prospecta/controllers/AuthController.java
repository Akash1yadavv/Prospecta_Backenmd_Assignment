package com.prospecta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospecta.dtos.LoginResponseDto;
import com.prospecta.dtos.LoginUserDto;
import com.prospecta.model.User;
import com.prospecta.services.JwtService;
import com.prospecta.services.UserService;

@RestController
@RequestMapping("/api/prospecta/auth") 
public class AuthController {
	@Autowired private UserService userService;
    @Autowired private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
    	
        User authenticatedUser = userService.loginUser(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
	
	
}
