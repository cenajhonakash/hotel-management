package com.aent.hotel.service.hotelservices.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HotelDto {

	private UUID hotelUuid;
	private String name;
	private Integer starRating;
	private String location;
	private String about;
}
