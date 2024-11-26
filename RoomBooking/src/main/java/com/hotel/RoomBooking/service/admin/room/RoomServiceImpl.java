package com.hotel.RoomBooking.service.admin.room;

import com.hotel.RoomBooking.dto.RoomDto;
import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.entity.Room;
import com.hotel.RoomBooking.entity.mapper.RoomMapper;
import com.hotel.RoomBooking.exceptions.RoomException;
import com.hotel.RoomBooking.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private RoomMapper roomMapper;


    public RoomResponseDto createRoom(RoomDto roomDto) throws RoomException {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try{
            Room room = roomMapper.roomDtoToRoom(roomDto);
            Room savedRoom = roomRepository.save(room);
            if(savedRoom.getId()>0){
                roomResponseDto.setStatusCode(201);
                roomResponseDto.setMessage("Successful");
                roomResponseDto.roomToRoomDto(savedRoom);
            }
            return roomResponseDto;

        } catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
    }

    public RoomResponseDto getAllRooms() throws RoomException {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try{
            List<Room> rooms = roomRepository.findAll();
            if (rooms.isEmpty()){
                roomResponseDto.setStatusCode(500);
                roomResponseDto.setError("No Rooms Found");
                roomResponseDto.getRoomList();
            }
            else {
                roomResponseDto.setStatusCode(200);
                roomResponseDto.setMessage("Successful");
                roomResponseDto.setRoomList(rooms.stream().map(room -> roomMapper.roomToRoomDto(room)).toList());
                return roomResponseDto;
            }
        } catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
    }

}
