package com.aent.hotel.service.hotelservices.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aent.hotel.service.hotelservices.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

}
