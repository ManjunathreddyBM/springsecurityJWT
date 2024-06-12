package com.manju.springsecurity.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manju.springsecurity.jwt.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User save(User user);
}
