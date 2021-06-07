package com.spring.webapp.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.webapp.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String id);
	
	@Query(value="select * from users u where u.email_verification_status = 'true'",
			countQuery = "select cont(*) from users u where u.email_verification_status = 'true'",
			nativeQuery = true)
	Page<UserEntity> findAllUserWIthComfirmedEmailAddress(Pageable paegeableRequest);
	
	
	
}
