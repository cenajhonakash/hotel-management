package com.aent.hotel.service.hotelservices.controller.external;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aent.hotel.service.hotelservices.dto.ApiResponseDto;
import com.aent.hotel.service.hotelservices.dto.HotelDto;
import com.aent.hotel.service.hotelservices.service.HotelService;

@RestController()
@RequestMapping("/v1/external/hotel")
public class HotelController {

	private final transient HotelService hotelService;

	@Autowired
	public HotelController(final HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@GetMapping("/fetch/{hotelId}")
	public ResponseEntity<HotelDto> getHotelDetails(@PathVariable("hotelId") UUID hotelId) {
		return new ResponseEntity<>(this.hotelService.fetchHotel(hotelId), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponseDto> createHotel(@RequestBody HotelDto hotelDto) {
		return new ResponseEntity<>(this.hotelService.createNewHotel(hotelDto), HttpStatus.OK);
	}

	@PatchMapping("/update")
	public ResponseEntity<ApiResponseDto> updateHotel(@RequestBody HotelDto hotelDto, @RequestParam("hotelId") UUID hotelId) {
		return new ResponseEntity<>(hotelService.updateHotel(hotelDto, hotelId), HttpStatus.OK);
	}
}
