package com.hotel.RoomBooking.repo;

import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByUserRole(UserRole userRole);
}
