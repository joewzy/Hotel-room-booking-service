package com.hotel.RoomBooking.service.admin.room;

import com.hotel.RoomBooking.dto.RoomDto;
import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.exceptions.RoomException;

public interface RoomService {

    RoomResponseDto createRoom(RoomDto roomDto) throws RoomException;

    RoomResponseDto getRooms(int pageNumber) throws RoomException;

    RoomResponseDto getRoomById(long id) throws RoomException;

    RoomResponseDto deleteRoomById(long id) throws RoomException;

    RoomResponseDto updateRoomById(long id, RoomDto roomDto) throws RoomException;
}
