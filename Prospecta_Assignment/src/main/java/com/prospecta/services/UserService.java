package com.prospecta.services;

import com.prospecta.dtos.LoginUserDto;
import com.prospecta.dtos.UserDto;
import com.prospecta.exceptions.UserException;
import com.prospecta.model.User;


public interface UserService {
	
	public User registerUser(UserDto user)throws UserException;

	public User loginUser(LoginUserDto loginDetails) throws UserException;
	

}
