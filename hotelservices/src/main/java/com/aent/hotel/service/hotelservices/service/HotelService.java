package com.aent.hotel.service.hotelservices.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aent.hotel.service.hotelservices.constants.ApiConstants;
import com.aent.hotel.service.hotelservices.dto.ApiResponseDto;
import com.aent.hotel.service.hotelservices.dto.HotelDto;
import com.aent.hotel.service.hotelservices.entity.Hotel;
import com.aent.hotel.service.hotelservices.exception.ResourceNotFoundException;
import com.aent.hotel.service.hotelservices.repository.HotelRepository;

@Service
public class HotelService {

	private final transient HotelRepository hotelRepository;
	private final transient ModelMapper modelMapper;

	@Autowired
	public HotelService(final HotelRepository hotelRepository, final ModelMapper modelMapper) {
		this.hotelRepository = hotelRepository;
		this.modelMapper = modelMapper;

	}

	public ApiResponseDto createNewHotel(HotelDto hoteldto) {
		ApiResponseDto response = null;
		try {
			Hotel toSave = this.modelMapper.map(hoteldto, Hotel.class);
			toSave.setCreatedDate(LocalDateTime.now());
			this.hotelRepository.save(toSave);
			response = ApiResponseDto.builder().exitcode(ApiConstants.SUCCESS_EXITCODE.getExitCode()).message(ApiConstants.CREATE_SUCCESS_MESSAGE.getMessage())
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

	public HotelDto fetchHotel(UUID hotelId) {
		Hotel entity = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException());
		return this.modelMapper.map(entity, HotelDto.class);
	}

	public ApiResponseDto updateHotel(HotelDto hoteldto, UUID hotelId) {
		Hotel entity = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException());
		Hotel toSave = this.modelMapper.map(hoteldto, Hotel.class);
		toSave.setHotelUuid(entity.getHotelUuid());
		toSave.setUpdatedDate(LocalDateTime.now());
		toSave.setCreatedDate(entity.getCreatedDate());
		ApiResponseDto response = null;
		try {
			this.hotelRepository.save(toSave);
			response = ApiResponseDto.builder().exitcode(ApiConstants.SUCCESS_EXITCODE.getExitCode()).message(ApiConstants.UPDATE_SUCCESS_MESSAGE.getMessage())
				.status(HttpStatus.OK).build();
		} catch (Exception e) {
			if (e instanceof SQLException) {
				response = ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode()).message(ApiConstants.GENERAL_ERROR.getMessage())
					.severity(ApiConstants.GENERAL_ERROR.getSeverity()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			} else {
				response = ApiResponseDto.builder().exitcode(ApiConstants.ERROR_EXITCODE.getExitCode()).message(ApiConstants.UNEXPECTED_ERROR.getMessage())
					.severity(ApiConstants.GENERAL_ERROR.getSeverity()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		return response;
	}
}
