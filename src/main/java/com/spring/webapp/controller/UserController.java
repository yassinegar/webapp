package com.spring.webapp.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.webapp.exceptions.UserServiceException;
import com.spring.webapp.model.request.UserDetailsRequestModel;
import com.spring.webapp.model.response.AddressesRest;
import com.spring.webapp.model.response.ErrorMessages;
import com.spring.webapp.model.response.OperationStatusModel;
import com.spring.webapp.model.response.RequestOperationNamed;
import com.spring.webapp.model.response.RequestOperationStatus;
import com.spring.webapp.model.response.UserRest;
import com.spring.webapp.service.AddresseService;
import com.spring.webapp.service.UserService;
import com.spring.webapp.shared.dto.AdressDto;
import com.spring.webapp.shared.dto.UserDto;

@RestController
@RequestMapping("users")
//@CrossOrigin(origins={"http://localhost:8080","http://localhost:8082"})
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	AddresseService addresseService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel user) throws Exception {

//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(user, userDto);

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);

		UserDto createUser = userService.createUser(userDto);

//		BeanUtils.copyProperties(createUser, returnValue);
		UserRest returnValue = modelMapper.map(createUser, UserRest.class);
		return returnValue;

	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest user = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);

		BeanUtils.copyProperties(userDto, user);
		return user;
	}

	@PutMapping(path = "/{idUser}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String idUser, @RequestBody UserDetailsRequestModel user) {
		UserRest returnValue = new UserRest();

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(user, userDto);

		UserDto updatesUser = userService.updateUser(idUser, userDto);

		BeanUtils.copyProperties(updatesUser, returnValue);

		return returnValue;

	}

	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
//	@CrossOrigin(origins={"http://localhost:8080","http://localhost:8082"})
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<UserRest> returnList = new ArrayList<>();

		List<UserDto> users = userService.getUsers(page, limit);

		for (UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnList.add(userModel);
		}

		return returnList;
	}

	@DeleteMapping(path = "/{idUser}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String idUser) {
		OperationStatusModel returnValue = new OperationStatusModel();

		returnValue.setOperationName(RequestOperationNamed.DELETE.name());

		userService.deleteUser(idUser);

		returnValue.setOperationResult(RequestOperationStatus.SUCESS.name());

		return returnValue;

	}

	@GetMapping(path = "/{id}/addresses", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<AddressesRest> getUserAddresses(@PathVariable String id) {
		List<AddressesRest> returnValue = new ArrayList<>();

		List<AdressDto> adressList = addresseService.getAddresses(id);

		if (adressList != null && !adressList.isEmpty()) {
			Type listType = new TypeToken<List<AddressesRest>>() {
			}.getType();
			returnValue = new ModelMapper().map(adressList, listType);
		}

		return returnValue;
	}

	@GetMapping(path = "/{id}/addresses/{AddressId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public AddressesRest getUserAddresse(@PathVariable String AddressId) {

		AdressDto adressDto = addresseService.getAddress(AddressId);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(adressDto, AddressesRest.class);

		 
	}

}