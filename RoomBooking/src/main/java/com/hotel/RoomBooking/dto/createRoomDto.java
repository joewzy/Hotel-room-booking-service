package com.hotel.RoomBooking.dto;

import com.hotel.RoomBooking.enums.RoomType;
import lombok.Data;

@Data
public class createRoomDto {

    private String name;

    private RoomType roomType;

    private Long price;

    private Boolean available;
}
