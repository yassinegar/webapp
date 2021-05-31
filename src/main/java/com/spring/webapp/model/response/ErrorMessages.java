package com.spring.webapp.model.response;

public enum ErrorMessages {

	MISSING_REQUIERD_FIELD("Missing requierd field. Please check documentation for requierd fields"),
	RECORD_ALREADY_EXISTS("Record already exists"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id is not found"),
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADRESS_NOT_VERIFIED("Email address coul no be verified");
	
	private String errorMessage;

	ErrorMessages(String errorMessage) {
		
		this.errorMessage = errorMessage;
		
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMesage) {
		this.errorMessage = errorMesage;
	}
	
}
