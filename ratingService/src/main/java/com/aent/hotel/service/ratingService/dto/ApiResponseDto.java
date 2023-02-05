package com.aent.hotel.service.ratingService.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseDto {

	private String message;
	private int exitcode;
	private String severity;
	private HttpStatus status;
	private String errorMessage;
}
