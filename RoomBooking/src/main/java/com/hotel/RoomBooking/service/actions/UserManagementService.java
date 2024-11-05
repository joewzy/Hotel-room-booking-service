package com.hotel.RoomBooking.service.actions;

import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {

    @Autowired
    private UserRepo userRepo;

    public RequestResponse getAllUsers(){
        RequestResponse response = new RequestResponse();
        try{
            List<Users> usersList = userRepo.findAll();
            if (!usersList.isEmpty()){
                response.setStatusCode(200);
                response.setMessage("Successful");
                response.setAllUsersList(usersList);
            }
            else {
                response.setStatusCode(404);
                response.setMessage("Users not found");
            }
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Encountered an error "+ e.getMessage());
            throw new RuntimeException(e);
        }
        return response;
    }

}
