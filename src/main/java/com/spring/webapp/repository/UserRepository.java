package com.spring.webapp.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.webapp.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String id);
	
	@Query(value="select * from users u where u.email_verification_status = true",
			countQuery = "select count(*) from users u where u.email_verification_status = true",
			nativeQuery = true)
	Page<UserEntity> findAllUserWIthComfirmedEmailAddress(Pageable paegeableRequest);
	
	@Query(value="select * from users u where u.first_name = ?1", nativeQuery=true)
	List<UserEntity> findUserByFirstName(String firstName);
	
	@Query(value="select * from users u where u.last_name = :lastName", nativeQuery=true)
	List<UserEntity> findUserByLastName(@Param("lastName")String lastName);
	
	@Query(value="select * from users u where u.first_name LIKE %:keyword% or u.last_name LIKE %:keyword%", nativeQuery=true)
	List<UserEntity> findUserByKeyword(@Param("keyword")String keyword);
	
	@Query(value="select u.first_name, u.last_name from users u where u.first_name LIKE %:keyword% or u.last_name LIKE %:keyword%", nativeQuery=true)
	List<Object[]> findUseFirstNameAndLastNameByKeyword(@Param("keyword")String keyword);
	
	@Transactional
	@Modifying
	@Query(value="update users u set u.email_verification_status = :emailVerifcationStatus where u.user_id=:userId", nativeQuery=true)
	void updateUserEmailVerifcationStatus(@Param("emailVerifcationStatus")boolean emailVerifcationStatus,
			@Param("userId")String userId);
	
	@Query("select user from UserEntity user where user.userId=:userId")
	UserEntity findUserEntityByUserId(@Param("userId")String userId);
	
	
	@Query("select user.firstName,user.lastName from UserEntity user where user.userId=:userId")
	List<Object[]> getUserEntityFullNameById(@Param("userId")String userId);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserEntity u set u.emailVerificationStatus = :emailVerifcationStatus where u.userId=:userId")
	void updateUserEntityEmailVerifcationStatus(@Param("emailVerifcationStatus")boolean emailVerifcationStatus,
			@Param("userId")String userId);
	
	
	
}
