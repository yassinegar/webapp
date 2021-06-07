package com.spring.webapp.service;

import java.util.List;

import com.spring.webapp.shared.dto.AdressDto;

public interface AddresseService {
	
	List<AdressDto> getAddresses(String userId);
	AdressDto getAddress(String addressId);
	
}
