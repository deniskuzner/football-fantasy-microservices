package com.fon.footballfantasyuserservice.service.impl;

import static com.fon.footballfantasyuserservice.exception.UserException.UserExceptionCode.LOGIN_FAILED;
import static com.fon.footballfantasyuserservice.exception.UserException.UserExceptionCode.USERNAME_ALREADY_EXISTS;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyuserservice.domain.Role;
import com.fon.footballfantasyuserservice.domain.User;
import com.fon.footballfantasyuserservice.domain.UserRole;
import com.fon.footballfantasyuserservice.exception.UserException;
import com.fon.footballfantasyuserservice.repository.RoleRepository;
import com.fon.footballfantasyuserservice.repository.UserRepository;
import com.fon.footballfantasyuserservice.repository.UserRoleRepository;
import com.fon.footballfantasyuserservice.service.UserService;
import com.fon.footballfantasyuserservice.service.dto.LoginCredentials;

@Service
@Transactional
@Validated
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	public User login(LoginCredentials credentials) {
		User u = userRepository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
		if(u == null) {
			throw new UserException(LOGIN_FAILED, "Invalid username or password!");
		}
		return u;
	}
	
	@Override
	public User register(User user) {
		User u = userRepository.findByUsername(user.getUsername());
		if(u != null) {
			throw new UserException(USERNAME_ALREADY_EXISTS, "Username %s is already taken!", user.getUsername());
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role roleUser = roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(
				UserRole.builder().user(user).role(roleUser).build()
				)
		);
		return userRepository.save(user);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		User user = userRepository.findById(id).get();
		if(user != null) {
			user.setRoles(userRoleRepository.findByUser(user));
		}
		return user;
	}
	
	@Override
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user != null) {
			user.setRoles(userRoleRepository.findByUser(user));
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Long findFavouriteClubByUserId(Long userId) {
		return userRepository.findFavouriteClubByUserId(userId);
	}

}
