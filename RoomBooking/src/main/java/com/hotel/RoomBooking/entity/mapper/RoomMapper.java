package com.hotel.RoomBooking.entity.mapper;

import com.hotel.RoomBooking.dto.RoomDto;
import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.entity.Room;

public class RoomMapper {


    public RoomDto roomToRoomDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setName(room.getName());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setPrice(room.getPrice());
        roomDto.setAvailable(room.getAvailable());

        return roomDto;
    }

    public Room roomDtoToRoom(RoomDto roomDto){
        Room room = new Room();
        room.setName(roomDto.getName());
        room.setRoomType(roomDto.getRoomType());
        room.setPrice(roomDto.getPrice());
        room.setAvailable(roomDto.getAvailable());

        return room;
    }
}
