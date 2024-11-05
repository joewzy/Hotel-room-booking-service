package com.hotel.RoomBooking.service.actions;

import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.dto.UserDto;
import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.exceptions.UserException;
import com.hotel.RoomBooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                response.setAllUsersList(usersList.stream().map(Users::getUserDto).toList());
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

    public RequestResponse getUserById(long userID){
        RequestResponse response = new RequestResponse();
        try {
            Optional<Users> existingUser = userRepo.findById(userID);
            if (existingUser.isEmpty()){
                throw new UserException("User not found");
            }
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.responseMapper(existingUser.get());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Failed");
            response.setError("Error encountered: "+ e.getMessage());
//            throw new RuntimeException(e);
        }
        return response;
    }

    public RequestResponse deleteUserById(long userId){
        RequestResponse response = new RequestResponse();

        try {
            Optional<Users> user = userRepo.findById(userId);
            if (user.isEmpty()){
                throw  new UserException("User does not exist!");
            }
            userRepo.deleteById(userId);
            response.setStatusCode(200);
            response.setMessage("Delete user successful");
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Failed");
            response.setError("Error encountered: "+ e.getMessage());
        }
        return response;
    }

}
