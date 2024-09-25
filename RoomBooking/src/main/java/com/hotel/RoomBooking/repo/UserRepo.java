package com.hotel.RoomBooking.repo;

import com.hotel.RoomBooking.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
}
