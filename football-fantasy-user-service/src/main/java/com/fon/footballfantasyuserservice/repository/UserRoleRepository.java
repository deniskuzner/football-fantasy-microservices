package com.fon.footballfantasyuserservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fon.footballfantasyuserservice.domain.User;
import com.fon.footballfantasyuserservice.domain.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>{
	
	List<UserRole> findByUser(User user);

}
