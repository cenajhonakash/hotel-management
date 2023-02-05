package com.aent.hotel.service.ratingService.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aent.hotel.service.ratingService.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

	public List<Rating> findByUserUuid(UUID userUuid);

	public List<Rating> findByHotelUuid(UUID hotelUuid);

	public List<Rating> findByRating(int rating);
}
