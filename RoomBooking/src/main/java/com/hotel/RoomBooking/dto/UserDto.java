package com.hotel.RoomBooking.dto;

import com.hotel.RoomBooking.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private long id;
    private String name;
    private String email;
    private UserRole userRole;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
