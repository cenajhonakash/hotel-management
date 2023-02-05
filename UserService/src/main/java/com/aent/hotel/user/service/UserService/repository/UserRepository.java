package com.aent.hotel.user.service.UserService.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.aent.hotel.user.service.UserService.Entity.User;

@Component
public interface UserRepository extends JpaRepository<User, UUID> {

}
