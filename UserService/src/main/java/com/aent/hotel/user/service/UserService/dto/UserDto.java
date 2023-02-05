package com.aent.hotel.user.service.UserService.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class UserDto {

	private UUID userUuid;
	private String firstName;
	private String lastName;
	private String email;
	private String contact;
	private String address;
	private List<RatingDto> ratings;
}
