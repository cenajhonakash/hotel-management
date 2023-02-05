package com.aent.hotel.service.ratingService.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RatingDto {

	private UUID userUuid;
	private UUID hotelUuid;
	private int rating;
	private String feedback;
	private LocalDateTime createdDate;
}
