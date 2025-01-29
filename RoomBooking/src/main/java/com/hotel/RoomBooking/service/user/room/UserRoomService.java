package com.hotel.RoomBooking.service.user.room;

import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.exceptions.RoomException;

public interface UserRoomService {
    RoomResponseDto getAvailableRooms(int pageNumber) throws RoomException;
}
