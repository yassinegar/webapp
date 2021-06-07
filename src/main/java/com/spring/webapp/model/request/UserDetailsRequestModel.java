package com.spring.webapp.model.request;

import java.util.List;

public class UserDetailsRequestModel {

	
	private String firstName; 
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private List<AdressRequestModel> adresses;


	public List<AdressRequestModel> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<AdressRequestModel> adresses) {
		this.adresses = adresses;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
