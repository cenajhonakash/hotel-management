package com.aent.hotel.user.service.UserService.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class ApiResponseDto {
	private String message;
	private int exitcode;
	private String severity;
	private HttpStatus status;
	private String errorMessage;
}
