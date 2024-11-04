package com.hotel.RoomBooking.service.actions;

import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

    @Autowired
    private UserRepo userRepo;


}
