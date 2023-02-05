package com.aent.hotel.service.ratingService.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aent.hotel.service.ratingService.constants.ApiConstants;
import com.aent.hotel.service.ratingService.dto.ApiResponseDto;
import com.aent.hotel.service.ratingService.dto.RatingDto;
import com.aent.hotel.service.ratingService.entity.Rating;
import com.aent.hotel.service.ratingService.exception.InvalidNumberOfParametersException;
import com.aent.hotel.service.ratingService.exception.ResourceNotFoundException;
import com.aent.hotel.service.ratingService.repository.RatingRepository;

@Service
public class RatingService {

	private final transient RatingRepository ratingRepository;
	private final transient ModelMapper modelMapper;

	@Autowired
	public RatingService(final RatingRepository ratingRepository, final ModelMapper modelMapper) {
		this.ratingRepository = ratingRepository;
		this.modelMapper = modelMapper;
	}

	public List<RatingDto> getRatings(UUID userUuid, UUID hotelUuid, Integer stars) {
		List<RatingDto> ratingList = null;
		if (userUuid != null && hotelUuid == null && stars == null) {
			ratingList = getRatingsOfUser(userUuid);
		} else if (userUuid == null && hotelUuid != null && stars == null) {
			ratingList = getAllRatingOfHotel(hotelUuid);
		} else if (userUuid == null && hotelUuid == null && stars != null) {
			ratingList = getAllHotelByRating(stars);
		} else {
			throw new InvalidNumberOfParametersException();
		}
		return ratingList;
	}

	public ApiResponseDto createNewRating(RatingDto ratingDto) {
		ApiResponseDto response = null;
		try {
			Rating toSave = this.modelMapper.map(ratingDto, Rating.class);
			toSave.setCreatedDate(LocalDateTime.now());
			System.out.println(toSave.toString());
			ratingRepository.save(toSave);
			response = ApiResponseDto.builder().exitcode(ApiConstants.SUCCESS_EXITCODE.getExitCode()).message(ApiConstants.SUCCESS_MESSAGE.getMessage())
				.status(HttpStatus.OK).build();
		} catch (Exception e) {
			if (e instanceof SQLException) {
				response = ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode()).message(ApiConstants.GENERAL_ERROR.getMessage())
					.severity(ApiConstants.GENERAL_ERROR.getSeverity()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			} else {
				response = ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode()).message(ApiConstants.UNEXPECTED_ERROR.getMessage())
					.severity(ApiConstants.UNEXPECTED_ERROR.getSeverity()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		return response;
	}

	public RatingDto getRatingById(UUID ratingId) {
		return modelMapper.map(ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException()), RatingDto.class);
	}

	private List<RatingDto> getRatingsOfUser(UUID userUuid) {
		List<Rating> ratingList = ratingRepository.findByUserUuid(userUuid);
		if (ratingList == null || ratingList.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		List<RatingDto> raingDtoList = ratingList.stream().map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
		return raingDtoList;
	}

	private List<RatingDto> getAllRatingOfHotel(UUID hotelUuid) {
		List<Rating> ratingList = ratingRepository.findByHotelUuid(hotelUuid);
		if (ratingList == null || ratingList.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		List<RatingDto> raingDtoList = ratingList.stream().map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
		return raingDtoList;
	}

	private List<RatingDto> getAllHotelByRating(int stars) {
		List<Rating> ratingList = ratingRepository.findByRating(stars);
		if (ratingList == null || ratingList.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		List<RatingDto> raingDtoList = ratingList.stream().map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
		return raingDtoList;
	}

}
