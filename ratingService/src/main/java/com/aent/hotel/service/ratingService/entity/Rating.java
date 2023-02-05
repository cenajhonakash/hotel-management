package com.aent.hotel.service.ratingService.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_rating")
@ToString
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rating_uuid", insertable = false, updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID ratingUuid;

	@Type(type = "uuid-char")
	private UUID userUuid;

	@Type(type = "uuid-char")
	private UUID hotelUuid;

	private int rating;
	private String feedback;

	private LocalDateTime createdDate;
}
