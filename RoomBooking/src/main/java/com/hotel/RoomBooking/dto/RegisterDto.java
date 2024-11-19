package com.hotel.RoomBooking.dto;

import com.hotel.RoomBooking.enums.UserRole;
import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
