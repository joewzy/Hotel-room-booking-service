package com.hotel.RoomBooking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.RoomBooking.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

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
    private String message;
    private String error;
    private String statusCode;
    private String token;
    private String expirationTime;

}
