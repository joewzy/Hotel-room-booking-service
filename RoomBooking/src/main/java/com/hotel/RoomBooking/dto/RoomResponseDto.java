package com.hotel.RoomBooking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.RoomBooking.entity.Room;
import com.hotel.RoomBooking.enums.RoomType;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomResponseDto {

    private int statusCode;
    private String message;
    private String error;
    

    private Long id;

    private String name;

    private RoomType roomType;

    private float price;

    private Boolean available;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private List<RoomDto> roomList;


    public void roomToRoomDto(Room room){
        setId(room.getId());
        setName(room.getName());
        setRoomType(room.getRoomType());
        setPrice(room.getPrice());
        setAvailable(room.getAvailable());
        setCreatedOn(room.getCreatedOn());
        setUpdatedOn(room.getUpdatedOn());
    }
}
