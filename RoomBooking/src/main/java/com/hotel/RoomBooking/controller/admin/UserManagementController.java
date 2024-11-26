package com.hotel.RoomBooking.controller.admin;

import com.hotel.RoomBooking.dto.RegisterDto;
import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.service.admin.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/admin")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/register")
    public ResponseEntity<RequestResponse>registerUser(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(userManagementService.registerUser(registerDto));
    }

    @GetMapping("/get-users")
    public ResponseEntity<RequestResponse> getUsers(){
       return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<RequestResponse> getUserById(@PathVariable long id){
        return ResponseEntity.ok(userManagementService.getUserById(id));
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<RequestResponse> deleteUser(@PathVariable long id){
        return ResponseEntity.ok(userManagementService.deleteUserById(id));
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<RequestResponse> updateUser(@PathVariable long id, @RequestBody Users newUserInfo){
        return ResponseEntity.ok(userManagementService.updateUser(id, newUserInfo));
    }
}
