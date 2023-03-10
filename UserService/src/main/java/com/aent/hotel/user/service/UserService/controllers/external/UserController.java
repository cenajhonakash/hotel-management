package com.aent.hotel.user.service.UserService.controllers.external;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aent.hotel.user.service.UserService.dto.ApiResponseDto;
import com.aent.hotel.user.service.UserService.dto.RatingDto;
import com.aent.hotel.user.service.UserService.dto.UserDto;
import com.aent.hotel.user.service.UserService.service.UserRatingService;
import com.aent.hotel.user.service.UserService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/v1/external/user")
public class UserController {

	private final UserService userService;
	private final UserRatingService ratingService;

	@Autowired
	public UserController(final UserService userService, final UserRatingService ratingService) {
		this.userService = userService;
		this.ratingService = ratingService;
	}

	@PostMapping("/make")
	public ResponseEntity<ApiResponseDto> createNewUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.saveNewUser(userDto), HttpStatus.OK);
	}

	@GetMapping("/get/{userUuid}")
	@CircuitBreaker(name = "user_ratingService_breaker", fallbackMethod = "user_ratingService_fallBack")
	public ResponseEntity<UserDto> fetchUser(@PathVariable("userUuid") UUID userUuid) {
		return new ResponseEntity<>(userService.getUser(userUuid), HttpStatus.OK);
	}

	@GetMapping("/fetch/ratings/{userId}")
	public ResponseEntity<List<RatingDto>> getAllRatingsOfUser(@PathVariable("userId") UUID userId) {
		return new ResponseEntity<>(ratingService.getAllRatingsOfUser(userId), HttpStatus.OK);
	}
	
	public ResponseEntity<UserDto> user_ratingService_fallBack(UUID userId, Exception ex) {
		System.out.println("rating Service is down");
		return new ResponseEntity<>(UserDto.builder().userUuid(userId).address("xyz").email("xyz").build(), HttpStatus.OK);
	}
}
