package com.aent.hotel.service.hotelservices.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aent.hotel.service.hotelservices.constants.ApiConstants;
import com.aent.hotel.service.hotelservices.dto.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseDto> handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<>(ApiResponseDto.builder().exitcode(1).message(ApiConstants.RESOURCE_NOT_FOUND.getMessage())
			.errorMessage(e.getLocalizedMessage()).severity(ApiConstants.RESOURCE_NOT_FOUND.getSeverity()).status(HttpStatus.NOT_FOUND).build(),
			HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiResponseDto> handleOtherRuntimeException(SQLException e) {
		return new ResponseEntity<>(ApiResponseDto.builder().exitcode(1).message(ApiConstants.GENERAL_ERROR.getMessage()).errorMessage(e.getLocalizedMessage())
			.severity(ApiConstants.GENERAL_ERROR.getSeverity()).status(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponseDto> handleOtherRuntimeException(RuntimeException e) {
		return new ResponseEntity<>(ApiResponseDto.builder().exitcode(1).message(ApiConstants.GENERAL_ERROR.getMessage()).errorMessage(e.getLocalizedMessage())
			.severity(ApiConstants.GENERAL_ERROR.getSeverity()).status(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
