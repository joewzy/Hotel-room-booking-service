package com.hotel.RoomBooking.service.admin.room;

import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.dto.RoomDto;
import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.entity.Room;
import com.hotel.RoomBooking.entity.mapper.RoomMapper;
import com.hotel.RoomBooking.exceptions.RoomException;
import com.hotel.RoomBooking.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
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
            }
            else {
                roomResponseDto.setStatusCode(200);
                roomResponseDto.setMessage("Successful");
                roomResponseDto.setRoomList(rooms.stream().map(room -> roomMapper.roomToRoomDto(room)).toList());

            }
        } catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
        return roomResponseDto;
    }

    public RoomResponseDto getRoomById(long id) throws RoomException {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try{
            Optional<Room> existingRoom = roomRepository.findById(id);
            if(existingRoom.isPresent()){
                roomResponseDto.setStatusCode(200);
                roomResponseDto.setMessage("Successful");
                roomResponseDto.roomToRoomDto(existingRoom.get());

            }
            else{
                roomResponseDto.setStatusCode(500);
                roomResponseDto.setError("Room not found");
            }

        }
        catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
        return roomResponseDto;
    }

    public RoomResponseDto getByRoomType(String roomType) throws RoomException {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try{
            List<Room> rooms = roomRepository.findAll();
            if (rooms.isEmpty()){
                roomResponseDto.setStatusCode(500);
                roomResponseDto.setError("No Rooms Found");
            }
            else {
                roomResponseDto.setStatusCode(200);
                roomResponseDto.setMessage("Successful");
                roomResponseDto.setRoomList(rooms.stream().filter(room ->room.getRoomType().name().equals(roomType.toUpperCase()) )
                        .map(room -> roomMapper.roomToRoomDto(room))
                        .toList());

            }
        } catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
        return roomResponseDto;
    }

    public RoomResponseDto deleteRoomById(long id) throws RoomException {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try{
            Optional<Room> existingRoom = roomRepository.findById(id);
            if(existingRoom.isPresent()){
                roomResponseDto.setStatusCode(204);
                roomResponseDto.setMessage("Delete Successful");
                roomRepository.deleteById(id);

            }
            else{
                roomResponseDto.setStatusCode(500);
                roomResponseDto.setError("Room not found");
            }

        }
        catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
        return roomResponseDto;
    }

    public RoomResponseDto updateRoomById(long id, RoomDto roomDto) throws RoomException {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try{
            Optional<Room> existingRoom = roomRepository.findById(id);
            if(existingRoom.isPresent()){
                Room room = roomMapper.roomDtoToRoom(roomDto);
                Room theExistingRoom = existingRoom.get();
                theExistingRoom.setName(room.getName());
                theExistingRoom.setRoomType(room.getRoomType());
                theExistingRoom.setAvailable(room.getAvailable());
                if(room.getPrice()>=0){
                    theExistingRoom.setPrice(room.getPrice());
                }
                Room updatedRoom = roomRepository.save(theExistingRoom);
                roomResponseDto.setStatusCode(200);
                roomResponseDto.setMessage("Update Successful");
                roomResponseDto.roomToRoomDto(updatedRoom);

            }
            else{
                roomResponseDto.setStatusCode(500);
                roomResponseDto.setError("Room not found");
            }

        }
        catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
        return roomResponseDto;
    }

}
