package com.spring.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.webapp.entity.AddressEntity;
import com.spring.webapp.entity.UserEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long>{
	
	Iterable<AddressEntity> findAllByUserDetails(UserEntity userEntity);
	
	AddressEntity findByAddressId(String addressId);

}
