package com.hotel.RoomBooking.controller.userController;

import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.repo.UserRepo;
import com.hotel.RoomBooking.service.actions.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/admin")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("/getusers")
    public ResponseEntity<RequestResponse> getUsers(){
       return ResponseEntity.ok(userManagementService.getAllUsers());
    }
}
