package com.hotel.RoomBooking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse {

    //user details
    private long id;
    private String name;
    private String email;
    private UserRole userRole;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    // response messages
    private int statusCode;
    private String message;
    private List<UserDto> allUsersList;
    private String error;
    private String token;
    private String expirationTime;

    public void responseMapper(Users myUser){
        setId(myUser.getId());
        setName(myUser.getName());
        setEmail(myUser.getEmail());
        setUserRole(myUser.getUserRole());
        setCreatedOn(myUser.getCreatedAt());
        setUpdatedOn(myUser.getUpdatedAt());

    }
}
