package com.hotel.RoomBooking.controller.admin;

import com.hotel.RoomBooking.dto.RoomDto;
import com.hotel.RoomBooking.dto.RoomResponseDto;
import com.hotel.RoomBooking.exceptions.RoomException;
import com.hotel.RoomBooking.service.admin.room.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/admin/room")
public class RoomManagementController {

    @Autowired
    private RoomServiceImpl roomService;

    @PostMapping("/new")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto roomDto) throws RoomException {
        return ResponseEntity.ok(roomService.createRoom(roomDto));
    }

    @GetMapping("/getrooms")
    public ResponseEntity<?> getAllRooms() throws RoomException {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/getrooms/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable long id) throws RoomException {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping("/getroom")
    public ResponseEntity<?> getRoomByType(@RequestParam String roomType) throws RoomException {
        return ResponseEntity.ok(roomService.getByRoomType(roomType));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoomById(@PathVariable long id) throws RoomException {
        return ResponseEntity.ok(roomService.deleteRoomById(id));
    }

    @PutMapping("/getrooms/update/{id}")
    public ResponseEntity<?> updateRoomById(@PathVariable long id, @RequestBody RoomDto roomDto) throws RoomException {
        return ResponseEntity.ok(roomService.updateRoomById(id, roomDto));
    }

}
