package com.hotel.RoomBooking.service.user.room;

import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.entity.Room;
import com.hotel.RoomBooking.entity.mapper.RoomMapper;
import com.hotel.RoomBooking.exceptions.RoomException;
import com.hotel.RoomBooking.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoomServiceImpl implements UserRoomService {
    private final RoomRepository roomRepo;
    private final RoomMapper roomMapper;


    public RoomResponseDto getAvailableRooms(int pageNumber) throws RoomException {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try{
            int pageSize = 4;
            Pageable pageable = PageRequest.of(pageNumber,pageSize);
            Page<Room> roomPage = roomRepo.findAll(pageable);

            roomResponseDto.setStatusCode(200);
            roomResponseDto.setMessage("Successful");
            roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
            roomResponseDto.setTotalPages(roomPage.getTotalPages());
            roomResponseDto.setRoomList( roomPage.stream().filter(Room::getAvailable).map(room-> roomMapper.roomToRoomDto(room)).toList() );

        }
        catch (Exception e) {
            throw new RoomException("Error encountered: "+ e.getMessage());
        }
        return roomResponseDto;
    }


}
