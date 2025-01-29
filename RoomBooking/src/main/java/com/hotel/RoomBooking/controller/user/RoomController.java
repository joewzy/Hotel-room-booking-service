package com.hotel.RoomBooking.controller.user;

import com.hotel.RoomBooking.exceptions.RoomException;
import com.hotel.RoomBooking.service.user.room.UserRoomServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
public class RoomController {

    private final UserRoomServiceImpl roomService;

    public RoomController(UserRoomServiceImpl userRoomService) {
        this.roomService = userRoomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getAvailableRooms(@RequestParam(defaultValue = "0") int pageNumber) throws RoomException {
        return ResponseEntity.ok(roomService.getAvailableRooms(pageNumber));
    }
}
