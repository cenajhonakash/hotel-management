package com.aent.hotel.service.ratingService.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aent.hotel.service.ratingService.constants.ApiConstants;
import com.aent.hotel.service.ratingService.dto.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseDto> handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<>(
			ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode()).message(ApiConstants.RESOURCE_NOT_FOUND.getMessage())
			.errorMessage(e.getLocalizedMessage()).severity(ApiConstants.RESOURCE_NOT_FOUND.getSeverity()).status(HttpStatus.NOT_FOUND).build(),
			HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiResponseDto> handleOtherRuntimeException(SQLException e) {
		return new ResponseEntity<>(ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode())
			.message(ApiConstants.GENERAL_ERROR.getMessage()).errorMessage(e.getLocalizedMessage())
			.severity(ApiConstants.GENERAL_ERROR.getSeverity()).status(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponseDto> handleOtherRuntimeException(RuntimeException e) {
		return new ResponseEntity<>(ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode())
			.message(ApiConstants.UNEXPECTED_ERROR.getMessage()).errorMessage(e.getLocalizedMessage()).severity(ApiConstants.UNEXPECTED_ERROR.getSeverity())
			.status(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidNumberOfParametersException.class)
	public ResponseEntity<ApiResponseDto> handleMultipleParametersNotAllowedException(InvalidNumberOfParametersException e) {
		return new ResponseEntity<>(
			ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode()).message(ApiConstants.VALIDATION_ERROR.getMessage())
				.errorMessage(e.getLocalizedMessage()).severity(ApiConstants.VALIDATION_ERROR.getSeverity()).status(HttpStatus.BAD_REQUEST).build(),
			HttpStatus.BAD_REQUEST);
	}
}
