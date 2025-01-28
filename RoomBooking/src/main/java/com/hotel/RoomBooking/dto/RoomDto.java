package com.hotel.RoomBooking.dto;

import com.hotel.RoomBooking.enums.RoomType;
import lombok.Data;

@Data
public class RoomDto {
    private Long id;

    private String name;

    private RoomType roomType;

    private float price;

    private Boolean available;
}
