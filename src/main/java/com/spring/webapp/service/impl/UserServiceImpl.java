package com.spring.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.webapp.entity.UserEntity;
import com.spring.webapp.exceptions.UserServiceException;
import com.spring.webapp.model.response.ErrorMessage;
import com.spring.webapp.model.response.ErrorMessages;
import com.spring.webapp.model.response.UserRest;
import com.spring.webapp.repository.UserRepository;
import com.spring.webapp.service.UserService;
import com.spring.webapp.shared.Utils;
import com.spring.webapp.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		UserEntity storedUserDetails = userRepository.findByEmail(user.getEmail());
		if (storedUserDetails != null)
			throw new RuntimeException("Record already exists");

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		userEntity.setEncyptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);

		UserEntity storedUserDeatils = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(storedUserDeatils, returnValue);

		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncyptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserId(String id) {
		UserDto userDto = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;

	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());

		userEntity = userRepository.save(userEntity);

		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		List<UserDto> returnList = new ArrayList<>();
		
		if (page>0 ) page-=1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);

		List<UserEntity> users = usersPage.getContent();

		for (UserEntity userEntity : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnList.add(userDto);
		}

		return returnList;
	}

}
