package com.aent.hotel.user.service.UserService.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import com.aent.hotel.user.service.UserService.dto.HotelDto;
import com.aent.hotel.user.service.UserService.dto.RatingDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_uuid", insertable = false, updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID userUuid;

	private String firstName;
	private String lastName;
	private String email;
	private String contact;
	private String address;

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	@ColumnDefault("false")
	private boolean active;

	@Version
	private int version;

	@Transient
	private List<RatingDto> ratings;

	@Transient
	private HotelDto hotel;
}
