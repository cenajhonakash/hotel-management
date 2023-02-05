package com.aent.hotel.user.service.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {

	private String name;
	private Integer starRating;
	private String location;
	private String about;
}
