package com.aent.hotel.service.gateway.ApiGateway.controller;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aent.hotel.service.gateway.ApiGateway.dto.ApiResponseDto;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@GetMapping("/login")
	public ResponseEntity<ApiResponseDto> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient oktaClient,
		@AuthenticationPrincipal OidcUser user, Model model) {
		logger.info("user email : {}", user.getEmail());

		ApiResponseDto response = ApiResponseDto.builder().userId(user.getEmail()).accessToken(oktaClient.getAccessToken().getTokenValue())
			.refreshToken(oktaClient.getRefreshToken().getTokenValue()).tokenExpireAt(oktaClient.getAccessToken().getExpiresAt().getEpochSecond())
			.authorities(user.getAuthorities().stream().map(grants -> {
				return grants.getAuthority();
			}).collect(Collectors.toList())).build();

		return new ResponseEntity<ApiResponseDto>(response, HttpStatus.OK);

	}
}
