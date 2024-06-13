package com.manju.springsecurity.jwt.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
@Setter
public class UserDto {

	private String firstName;
	
	private String lastNAme;
	
	private String email;
	
	private String password;
	
	private String role;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNAme() {
		return lastNAme;
	}

	public void setLastNAme(String lastNAme) {
		this.lastNAme = lastNAme;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastNAme=" + lastNAme + ", email=" + email + ", password="
				+ password + ", role=" + role + "]";
	}
	
}
