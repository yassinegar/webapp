package com.spring.webapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.spring.webapp.entity.UserEntity;
import com.spring.webapp.repository.UserRepository;
import com.spring.webapp.shared.dto.UserDto;

class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	
	@Mock
	UserRepository userRepository;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		
	}

	@Test
	final void testGetUser() {
		
		
		UserEntity userEntity = new UserEntity();
		
		userEntity.setId(1L);
		userEntity.setFirstName("Yassine");
		userEntity.setUserId("hhien68989");
		userEntity.setEncyptedPassword("kneofneofn565");
		
		
		
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
	
		
		UserDto userDto = userServiceImpl.getUser("yassine.garoui@gmail.com");
		
		
		assertNotNull(userDto);
		assertEquals("Yassine",userDto.getFirstName());
		
	}

}
