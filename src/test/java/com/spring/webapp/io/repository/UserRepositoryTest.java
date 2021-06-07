package com.spring.webapp.io.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.webapp.entity.AddressEntity;
import com.spring.webapp.entity.UserEntity;
import com.spring.webapp.repository.UserRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	static boolean recordsCreated = false;

	@BeforeEach
	void setUp() throws Exception {
		if(!recordsCreated)
		{
			createRecords();
		}
	
	}

	@Test
	final void testVerifiedUsers() {
		
		Pageable pageableRequest = PageRequest.of(0, 1);
		Page<UserEntity> page =  userRepository.findAllUserWIthComfirmedEmailAddress(pageableRequest);
		assertNotNull(page);
		
		List<UserEntity> userEntities = page.getContent();
		assertNotNull(userEntities);
		assertTrue(userEntities.size() == 1);
	}
	
	@Test
	final void testFindUsersByFirstName() {
		
		String firstName = "yass";
		List<UserEntity> users =  userRepository.findUserByFirstName(firstName);
		assertNotNull(users);
		assertTrue(users.size() == 2);
		
		UserEntity user = users.get(0);
		assertTrue(user.getFirstName().equals(firstName));
		
	}
	
	
	
	@Test
	final void testFindUsersByLastName() {
		
		String lastName = "Garoui";
		List<UserEntity> users =  userRepository.findUserByLastName(lastName);
		assertNotNull(users);
		assertTrue(users.size() == 2);
		
		UserEntity user = users.get(0);
		assertTrue(user.getLastName().equals(lastName));
		
	}
	
	@Test
	final void testFindUsersByKeyword() {
		
		String keyword = "yass";
		List<UserEntity> users =  userRepository.findUserByKeyword(keyword);
		assertNotNull(users);
		assertTrue(users.size() == 2);
		
		UserEntity user = users.get(0);
		assertTrue(user.getLastName().contains(keyword)
		|| user.getFirstName().contains(keyword) ) ;
		
	}

	
	@Test
	final void testFindUserFirstNameAndLastNameByKeyword() {
		
		String keyword = "as";
		List<Object[]> users =  userRepository.findUseFirstNameAndLastNameByKeyword(keyword);
		assertNotNull(users);
		assertTrue(users.size() == 2);
		
		Object[] user = users.get(0);
		assertTrue(user.length == 2);
		
		String userFirstName = String.valueOf(user[0]);
		String userLastName = String.valueOf(user[1]);
		
		
		assertNotNull(userFirstName);
		
		assertNotNull(userLastName);
		
		System.out.println(userFirstName);
		System.out.println(userLastName);
		
		
	}
	
	
	@Test
	final void updateUserEmailVerifcationStatus()
	{
		boolean emailVerficationStatus = true;
		String userId = "OvkZrY3751VEbpFWhGxcUcP26kC9c5";
		
		userRepository.updateUserEmailVerifcationStatus(emailVerficationStatus, userId);
		
		UserEntity storedUserDetails = userRepository.findByUserId(userId);
		
		boolean storedEmailVerficationStatus = storedUserDetails.getEmailVerificationStatus();
		
		assertTrue(storedEmailVerficationStatus == emailVerficationStatus);
		
		
			}

	@Test
	final void findUserEntityByUserId()
	{
		
		String userId = "OvkZrY3751VEbpFWhGxcUcP26kC9c5";
		UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
		
		assertNotNull(userEntity);
		assertTrue(userEntity.getUserId().equals(userId));  
		
	}
	
	
	@Test
	final void getUserEntityFullNameById()
	{
		
		String userId = "acsc";
		List<Object[]> users =  userRepository.getUserEntityFullNameById(userId);
		assertNotNull(users);
		assertTrue(users.size() == 1);
		
		Object[] user = users.get(0);
		assertTrue(user.length == 2);
		
		String userFirstName = String.valueOf(user[0]);
		String userLastName = String.valueOf(user[1]);
		
		
		assertNotNull(userFirstName);
		
		assertNotNull(userLastName);
		
		System.out.println(userFirstName);
		System.out.println(userLastName);
		
	}
	
	
	@Test
	final void updateUserEntityEmailVerifcationStatus()
	{
		boolean emailVerficationStatus = false;
		String userId = "acsc";
		
		userRepository.updateUserEntityEmailVerifcationStatus(emailVerficationStatus, userId);
		
		UserEntity storedUserDetails = userRepository.findByUserId(userId);
		
		boolean storedEmailVerficationStatus = storedUserDetails.getEmailVerificationStatus();
		
		assertTrue(storedEmailVerficationStatus == emailVerficationStatus);
		
		
			}
	
	private void createRecords()
	{
		
		
		
		UserEntity user2 = new UserEntity();
		user2.setFirstName("a");
		user2.setLastName("a");
		user2.setUserId("acsc");
		user2.setEncyptedPassword("a");
		user2.setEmail("aef");
		user2.setEmailVerificationStatus(true);
		
		AddressEntity address2 = new AddressEntity();
		address2.setType("shipping");
		address2.setAddressId("ffefeascs");
		address2.setCity("a");
		address2.setCountry("a");
		address2.setPostCode("ada");
		address2.setStreetName("afezaf");
		
		List<AddressEntity> addresses2 = new ArrayList<>();
		
		addresses2.add(address2);
		
		user2.setAdresses(addresses2);
		
		
		//userRepository.save(user2);
		
		 recordsCreated = true;
	}
	
}
