package com.hotel.RoomBooking.dto;

import com.hotel.RoomBooking.enums.RoomType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomDto {

    private Long id;

    private String name;

    private RoomType roomType;

    private Long price;

    private Boolean available;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
