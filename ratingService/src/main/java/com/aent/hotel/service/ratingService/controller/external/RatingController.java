package com.aent.hotel.service.ratingService.controller.external;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aent.hotel.service.ratingService.dto.ApiResponseDto;
import com.aent.hotel.service.ratingService.dto.RatingDto;
import com.aent.hotel.service.ratingService.service.RatingService;

@RestController
@RequestMapping("/v1/external/rating")
public class RatingController {

	private final transient RatingService ratingService;

	@Autowired
	public RatingController(final RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@GetMapping("/fetch")
	public ResponseEntity<List<RatingDto>> getHotelRating(@RequestParam(name = "userId", required = false) UUID userUuid,
		@RequestParam(name = "hotelId", required = false) UUID hotelUuid, @RequestParam(name = "stars", required = false) Integer stars) {
		return new ResponseEntity<>(ratingService.getRatings(userUuid, hotelUuid, stars), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponseDto> createHotelrating(@RequestBody RatingDto ratingDto) {
		return new ResponseEntity<>(ratingService.createNewRating(ratingDto), HttpStatus.OK);
	}
}
