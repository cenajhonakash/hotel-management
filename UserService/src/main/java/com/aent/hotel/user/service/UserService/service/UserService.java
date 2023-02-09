package com.aent.hotel.user.service.UserService.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aent.hotel.user.service.UserService.Entity.User;
import com.aent.hotel.user.service.UserService.Exception.ResourceNotFoundException;
import com.aent.hotel.user.service.UserService.constants.ApiConstants;
import com.aent.hotel.user.service.UserService.dto.ApiResponseDto;
import com.aent.hotel.user.service.UserService.dto.UserDto;
import com.aent.hotel.user.service.UserService.repository.UserRepository;

@Service
public class UserService {

	private final transient UserRepository userRepository;
	private final transient ModelMapper mapper;
	private final transient UserRatingService ratingService;

	@Autowired
	public UserService(final UserRepository userRepository, final ModelMapper mapper, final UserRatingService ratingService) {
		this.userRepository = userRepository;
		this.mapper = mapper;
		this.ratingService = ratingService;

	}

	public UserDto getUser(UUID userUuid) {
		User entity = this.userRepository.findById(userUuid).orElseThrow(() -> new ResourceNotFoundException());
		entity.setRatings(ratingService.getAllRatingsOfUser(userUuid));
		return this.mapper.map(entity, UserDto.class);
	}

	@Transactional
	public ApiResponseDto saveNewUser(UserDto userDto) {
		ApiResponseDto response;
		try {
			User toSave = this.mapper.map(userDto, User.class);
			toSave.setCreatedDate(LocalDateTime.now());
			this.userRepository.save(toSave);
			response = ApiResponseDto.builder().exitcode(0).message("User saved successfully.").status(HttpStatus.OK).build();
		} catch (Exception e) {
			if (e instanceof SQLException) {
				response = ApiResponseDto.builder().exitcode(1).message(ApiConstants.GENERAL_ERROR.getMessage()).errorMessage(e.getLocalizedMessage())
					.status(HttpStatus.OK)
					.severity(ApiConstants.GENERAL_ERROR.getSeverity()).build();
			} else {
				response = ApiResponseDto.builder().exitcode(1).message(ApiConstants.UNEXPECTED_ERROR.getMessage()).errorMessage(e.getLocalizedMessage())
					.status(HttpStatus.OK)
					.severity(ApiConstants.UNEXPECTED_ERROR.getSeverity()).build();
			}
		}
		return response;
	}
}
