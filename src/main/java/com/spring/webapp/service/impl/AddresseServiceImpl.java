package com.spring.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.webapp.entity.AddressEntity;
import com.spring.webapp.entity.UserEntity;
import com.spring.webapp.repository.AddressRepository;
import com.spring.webapp.repository.UserRepository;
import com.spring.webapp.service.AddresseService;
import com.spring.webapp.shared.dto.AdressDto;

@Service
public class AddresseServiceImpl implements AddresseService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	
	@Override
	public List<AdressDto> getAddresses(String userId) {
		List<AdressDto> returnValue = new ArrayList<>();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if (userEntity == null) return returnValue;
		
		Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
		
		
		for(AddressEntity addressEntity:addresses)
		{
			returnValue.add(new ModelMapper().map(addressEntity,AdressDto.class));
		}
		
		return returnValue;
		
		
	}


	@Override
	public AdressDto getAddress(String addressId) {
	
		AdressDto returnValue = null;
		
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		
		if (addressEntity != null)
		{
			returnValue = new ModelMapper().map(addressEntity, AdressDto.class);
		}
		
		return returnValue;
		
	}

}
