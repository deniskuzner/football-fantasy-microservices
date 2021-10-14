package com.fon.footballfantasygateway.repository;

import org.springframework.data.repository.CrudRepository;

import com.fon.footballfantasygateway.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
