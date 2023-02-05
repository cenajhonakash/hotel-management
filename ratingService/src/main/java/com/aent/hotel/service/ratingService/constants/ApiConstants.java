package com.aent.hotel.service.ratingService.constants;

public enum ApiConstants {
	RESOURCE_NOT_FOUND("Resource not found", "INFO"), RESOURCE_ALREADY_PRESENT("Resource already exists in system", "INFO"), GENERAL_ERROR("General Error",
		"FATAL"), UNEXPECTED_ERROR("Unexpected Error occured", "SEVERE"), ERROR_EXITCODE(
			1), SUCCESS_EXITCODE(
				0), SUCCESS_MESSAGE("Success!!",
					"INFO"), UPDATE_SUCCESS_MESSAGE("Successfully updated!!", "INFO"), VALIDATION_ERROR("Validation not passed", "ERROR");

	private String message;
	private String severity;
	private int exitCode;

	ApiConstants(String message, String severity) {
		this.message = message;
		this.severity = severity;
	}
	ApiConstants(int exitCode) {
		this.exitCode = exitCode;
	}
	public String getSeverity() {
		return severity;
	}
	public String getMessage() {
		return message;
	}
	public int getExitCode() {
		return exitCode;
	}
}
