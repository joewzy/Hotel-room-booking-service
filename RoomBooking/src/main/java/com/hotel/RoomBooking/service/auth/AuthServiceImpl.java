package com.hotel.RoomBooking.service.auth;

import com.hotel.RoomBooking.dto.LoginDto;
import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.dto.SignUpDto;
import com.hotel.RoomBooking.dto.UserDto;
import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.enums.UserRole;
import com.hotel.RoomBooking.exceptions.UserException;
import com.hotel.RoomBooking.repo.UserRepo;
import com.hotel.RoomBooking.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    //create default admin if none exists
    // this runs when application starts

    @PostConstruct
    public void createAdminAccount(){
        Optional<Users> adminUser = userRepo.findByUserRole(UserRole.ADMIN);
        if(adminUser.isEmpty()){
            Users defaultAdminAcc = new Users();
            defaultAdminAcc.setName("admin 1");
            defaultAdminAcc.setEmail("admin@admin.com");
            defaultAdminAcc.setPassword(passwordEncoder.encode("admin"));
            defaultAdminAcc.setUserRole(UserRole.ADMIN);
            userRepo.save(defaultAdminAcc);
            System.out.println("Default Admin Account Created successfully");
        }
        else {
            System.out.println("Default admin account exists");
        }
    }

    //create new Customer
    public RequestResponse createUser(SignUpDto signUpRequest) throws UserException {
        RequestResponse response = new RequestResponse();
        try {

            Optional<Users> existingUser = userRepo.findByEmail(signUpRequest.getEmail());
            if(existingUser.isPresent()){
                response.setStatusCode(403);
                response.setMessage("Failed to create user");
                response.setError("User with email exists "+ signUpRequest.getEmail());
                return response;
//                throw new UserException("User with email exists "+ signUpRequest.getEmail());
            }
            Users newUser = new Users();
            newUser.setName(signUpRequest.getName());
            newUser.setEmail(signUpRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            newUser.setUserRole(UserRole.CUSTOMER);
            Users savedUser = userRepo.save(newUser);

            if (savedUser.getId()>0) {
                response.setMessage("Account Created successfully");
                response.setStatusCode(201);
                response.responseMapper(newUser);
            }

        }
        catch (Exception e) {
            throw new UserException(e.toString());
        }
        return response;
    }

    public RequestResponse login(LoginDto loginDto){
        RequestResponse response = new RequestResponse();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
                    )
            );
            Optional<Users> theUser = userRepo.findByEmail(loginDto.getEmail());
            if (theUser.isEmpty()){
                throw new UserException("Invalid Credentials");
            }
            String jwt = jwtUtil.generateToken(theUser.get());
            response.setId(theUser.get().getId());
            response.setEmail(theUser.get().getEmail());
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setMessage("Login successful");
            response.setExpirationTime("24 Hours");

        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
}
