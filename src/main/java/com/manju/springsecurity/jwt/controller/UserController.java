package com.manju.springsecurity.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manju.springsecurity.jwt.dto.LoginDto;
import com.manju.springsecurity.jwt.dto.UserDto;
import com.manju.springsecurity.jwt.model.User;
import com.manju.springsecurity.jwt.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody UserDto userDto) {
		User user = userService.register(userDto);
		return new ResponseEntity(user, HttpStatusCode.valueOf(200));
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
		
		return new ResponseEntity(userService.login(loginDto), HttpStatusCode.valueOf(200));
		
	}
}
