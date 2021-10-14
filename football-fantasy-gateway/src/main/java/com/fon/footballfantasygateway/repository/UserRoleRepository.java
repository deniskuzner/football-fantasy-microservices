package com.fon.footballfantasygateway.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fon.footballfantasygateway.domain.User;
import com.fon.footballfantasygateway.domain.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>{
	
	List<UserRole> findByUser(User user);

}
