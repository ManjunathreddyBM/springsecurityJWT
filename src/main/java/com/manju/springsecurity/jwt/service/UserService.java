package com.manju.springsecurity.jwt.service;

import org.springframework.stereotype.Service;

import com.manju.springsecurity.jwt.dto.LoginDto;
import com.manju.springsecurity.jwt.dto.UserDto;
import com.manju.springsecurity.jwt.model.User;

public interface UserService {
	User register(UserDto user);
	
	User login(LoginDto loginDto);
}
