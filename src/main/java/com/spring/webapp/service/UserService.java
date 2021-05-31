package com.spring.webapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.webapp.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	
	UserDto getUser(String email);

	UserDto getUserByUserId(String id);

	UserDto updateUser(String id, UserDto userDto);
	
	List<UserDto> getUsers(int page,int limit);

}
