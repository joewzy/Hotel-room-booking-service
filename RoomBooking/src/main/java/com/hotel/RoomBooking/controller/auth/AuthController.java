package com.hotel.RoomBooking.controller.auth;

import com.hotel.RoomBooking.dto.LoginDto;
import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.dto.SignUpDto;
import com.hotel.RoomBooking.exceptions.UserException;
import com.hotel.RoomBooking.service.auth.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<RequestResponse> signUpUser(@RequestBody SignUpDto signUpRequest) throws UserException {
        return  ResponseEntity.ok(authService.createUser(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<RequestResponse> loginUser(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}
