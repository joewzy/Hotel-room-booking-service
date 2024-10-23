package com.hotel.RoomBooking.service.auth;

import com.hotel.RoomBooking.dto.SignUpDto;
import com.hotel.RoomBooking.dto.UserDto;
import com.hotel.RoomBooking.exceptions.UserException;

public interface AuthService {

    UserDto createUser(SignUpDto signUpRequest) throws UserException;
}
