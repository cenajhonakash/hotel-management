package com.aent.hotel.user.service.UserService.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aent.hotel.user.service.UserService.Exception.ResourceNotFoundException;
import com.aent.hotel.user.service.UserService.dto.HotelDto;

@Service
public class HotelService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${hotel.service.hostname}")
	private String HOTEL_SERVICE_HOST;

	@Value("${hotel.service.fetchHotel.endpoint.suffix}")
	private String HOTEL_SERVICE__FETCH_SUFFIX;

	public HotelDto getHotelByIdForUser(UUID hotelId) {
		String uri = HOTEL_SERVICE_HOST + HOTEL_SERVICE__FETCH_SUFFIX + hotelId;
		HotelDto res = null;
		try {
			ResponseEntity<HotelDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, HotelDto.class);
			if (response == null || response.getBody() == null) {
				throw new ResourceNotFoundException();
			}
			res = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
