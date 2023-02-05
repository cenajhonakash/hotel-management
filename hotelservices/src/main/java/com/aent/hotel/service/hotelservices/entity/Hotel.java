package com.aent.hotel.service.hotelservices.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hotel_uuid", insertable = false, updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID hotelUuid;

	private String name;
	private Integer starRating;
	private String location;
	private String about;

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
