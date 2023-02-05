package com.aent.hotel.user.service.UserService.constants;

public enum ApiConstants {
	RESOURCE_NOT_FOUND("Resource not found", "INFO"), RESOURCE_ALREADY_PRESENT("Resource already exists in system", "INFO"), GENERAL_ERROR("General Error",
		"FATAL"), UNEXPECTED_ERROR("Unexpected Error occured", "SEVERE"), REMOTE_EXCEPTION("Remote call failed.", "WARN");

	private String message;
	private String severity;
	ApiConstants(String message, String severity) {
		this.message = message;
		this.severity = severity;
	}
	public String getSeverity() {
		return severity;
	}
	public String getMessage() {
		return message;
	}
}
