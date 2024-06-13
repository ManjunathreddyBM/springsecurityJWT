package com.manju.springsecurity.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manju.springsecurity.jwt.dto.LoginDto;
import com.manju.springsecurity.jwt.dto.UserDto;
import com.manju.springsecurity.jwt.model.User;
import com.manju.springsecurity.jwt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User register(UserDto userDto) {
		User user = new User();
		System.out.println(userDto.toString());
		user.setFirstname(userDto.getFirstName());
		user.setLastname(userDto.getLastNAme());
		user.setEmail(userDto.getEmail());
		user.setRole(userDto.getRole());
		System.out.println("PASSWORD BEFORE ENCODE >>>>>> " + userDto.getPassword());
		String pass = passwordEncoder.encode(userDto.getPassword());
		System.out.println("PASSWORD AFTER ENCODE>>>>>> " + pass);
		user.setPassword(pass);
		return userRepository.save(user);
	}

	public User login(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getUserName());
		if(user == null) {
			throw new RuntimeException("USERNAME NOT FOUND...");
		}
		if(user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
			return user;
		else
			throw new RuntimeException("Wrong Password");
	}
}
