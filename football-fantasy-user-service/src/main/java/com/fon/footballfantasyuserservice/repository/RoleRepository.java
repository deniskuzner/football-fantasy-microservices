package com.fon.footballfantasyuserservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.fon.footballfantasyuserservice.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByName(String name);
	
}
