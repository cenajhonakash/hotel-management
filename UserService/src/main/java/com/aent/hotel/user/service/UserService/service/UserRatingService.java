package com.aent.hotel.user.service.UserService.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.aent.hotel.user.service.UserService.Exception.ResourceNotFoundException;
import com.aent.hotel.user.service.UserService.dto.RatingDto;

@Service
public class UserRatingService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Value("${rating.service.hostname}")
	private String RATING_SERVICE_HOST;

	@Value("${rating.service.fetchByUserId.endpoint.suffix}")
	private String RATING_SERVICE__FETCH_SUFFIX;

	public List<RatingDto> getAllRatingsOfUser(UUID userId) {

		String uri = RATING_SERVICE_HOST + RATING_SERVICE__FETCH_SUFFIX + userId;
		List<RatingDto> res = null;
		try {
			final ResponseEntity<RatingDto[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, RatingDto[].class);
			if (response == null || response.getBody() == null || response.getBody().length == 0) {
				throw new ResourceNotFoundException();
			}
			res = Arrays.asList(response.getBody());
			res.forEach(rating -> rating.setHotelDto(hotelService.getHotelByIdForUser(rating.getHotelUuid())));
			//			res.stream().map(ratingDto -> {
			//				ratingDto.setHotelDto(hotelService.getHotelByIdForUser(ratingDto.getUserUuid()));
			//				return ratingDto;
			//			}).collect(Collectors.toList());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
		return res;
	}
}
