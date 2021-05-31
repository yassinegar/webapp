package com.spring.webapp.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String userId;

	private String firstName;

	private String lastName;

	private String email;
	
	private String password;
	
	private String encyptedPassword;

	private String emailVerificationToken;
	
	private Boolean emailVerificationStatus = false;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getEncyptedPassword() {
		return encyptedPassword;
	}

	public void setEncyptedPassword(String encyptedPassword) {
		this.encyptedPassword = encyptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	
	
	
}
