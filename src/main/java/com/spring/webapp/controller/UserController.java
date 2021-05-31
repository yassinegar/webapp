package com.spring.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.spring.webapp.model.response.ErrorMessages;
import com.spring.webapp.model.response.UserRest;
import com.spring.webapp.service.UserService;
import com.spring.webapp.shared.dto.UserDto;


@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest createUser(@RequestBody UserDetailsRequestModel user) throws Exception{

		UserRest returnValue = new UserRest();
		
		if (user.getFirstName().isEmpty()) throw new NullPointerException("value is empty");
		
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(user, userDto);
		
		UserDto createUser = userService.createUser(userDto);
		
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
		
	}
	

	@GetMapping(path="/{id}",produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id)
	{
		UserRest user =new UserRest();
		
		UserDto userDto = userService.getUserByUserId(id);
		
		BeanUtils.copyProperties(userDto, user);
		return user;
	}
	
	@PutMapping(path="/{idUser}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String idUser,@RequestBody UserDetailsRequestModel user)
	{
		UserRest returnValue = new UserRest();
		
		
		
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(user, userDto);
		
		UserDto updatesUser = userService.updateUser(idUser,userDto);
		
		BeanUtils.copyProperties(updatesUser, returnValue);
		
		return returnValue;
		
		
	}
	
	@GetMapping(
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers(@RequestParam(value="page",defaultValue ="0")int page,@RequestParam(value="limit",defaultValue ="25")int limit )
	{
		List<UserRest> returnList = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers(page,limit);
		
		for (UserDto userDto : users)
		{
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnList.add(userModel);
		}
		
		return returnList;
	}
	
	
	
	
	
}