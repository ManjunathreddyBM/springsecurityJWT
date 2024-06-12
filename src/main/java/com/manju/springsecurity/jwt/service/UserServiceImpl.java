package com.manju.springsecurity.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manju.springsecurity.jwt.dto.UserDto;
import com.manju.springsecurity.jwt.model.User;
import com.manju.springsecurity.jwt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User register(UserDto userDto) {
		User user = new User();
		user.setFirstname(userDto.getFirstName());
		user.setLastname(userDto.getLastNAme());
		user.setEmail(user.getEmail());
		user.setRole(userDto.getRole());
		user.setPassword(userDto.getPassword());
		return userRepository.save(user);
	}

}
