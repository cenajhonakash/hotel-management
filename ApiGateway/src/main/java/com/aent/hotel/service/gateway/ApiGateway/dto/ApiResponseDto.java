package com.aent.hotel.service.gateway.ApiGateway.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto {

	private String userId;
	private String accessToken;
	private String refreshToken;
	private long tokenExpireAt;
	private Collection<String> authorities;

}
