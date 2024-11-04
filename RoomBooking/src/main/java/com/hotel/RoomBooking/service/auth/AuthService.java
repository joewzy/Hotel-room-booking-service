package com.hotel.RoomBooking.service.auth;

import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.dto.SignUpDto;
import com.hotel.RoomBooking.exceptions.UserException;

public interface AuthService {

    RequestResponse createUser(SignUpDto signUpRequest) throws UserException;
}
