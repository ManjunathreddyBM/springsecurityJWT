package com.manju.springsecurity.jwt.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manju.springsecurity.jwt.dto.LoginDto;
import com.manju.springsecurity.jwt.dto.LoginResponse;
import com.manju.springsecurity.jwt.dto.UserDto;
import com.manju.springsecurity.jwt.jwt.JwtService;
import com.manju.springsecurity.jwt.model.User;
import com.manju.springsecurity.jwt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

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
		if (user == null) {
			throw new RuntimeException("USERNAME NOT FOUND...");
		}
		boolean match = passwordEncoder.matches(passwordEncoder.encode(loginDto.getPassword()), user.getPassword());
		System.out.println("PASS MATCH >>> "+match);
		if (user != null && match)
			return user;
		else
			throw new RuntimeException("Wrong Password");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(">>>>>>>>>>>>>>>> loadUserByUsername <<<<<<<<<<<<<<<<<<");
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new RuntimeException("USERNAME NOT FOUND...");
		}
		return user;
		
	}

	@Override
	public LoginResponse loginWithJwt(LoginDto loginDto) {
		
		System.out.println(" Login started " + loginDto.toString());
		Authentication auth = null;
		try {
			auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		System.out.println(" Authenticated " + auth );

		}catch (Exception e) {
			System.out.println(" Authenticated " + auth );

			e.printStackTrace();
		}
		
		if ( auth.isAuthenticated()) {
			var jwt = jwtService.generateToken(loginDto.getUserName());
			var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), loginDto.getUserName());

			LoginResponse jwtAuthenticationResponse = new LoginResponse();
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshToken);
			System.out.println("TOKEN >>>> " + jwt + "\n" +"REFRESH TOKEN >>>> " + refreshToken);
			return jwtAuthenticationResponse;
		} else
			throw new RuntimeException("Wrong Password");

//        User user = userRepository.findByEmail(loginDto.getUserName()).orElseThrow(() ->
//                new IllegalArgumentException("Invalid Email id"));

	}
}
