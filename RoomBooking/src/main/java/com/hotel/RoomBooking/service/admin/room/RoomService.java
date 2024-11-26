package com.hotel.RoomBooking.service.admin.room;

import com.hotel.RoomBooking.dto.RoomDto;
import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.exceptions.RoomException;

public interface RoomService {

    RoomResponseDto createRoom(RoomDto roomDto) throws RoomException;
}
