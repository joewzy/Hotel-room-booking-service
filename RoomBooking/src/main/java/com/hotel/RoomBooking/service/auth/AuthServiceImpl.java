package com.hotel.RoomBooking.service.auth;

import com.hotel.RoomBooking.dto.LoginDto;
import com.hotel.RoomBooking.dto.RequestResponse;
import com.hotel.RoomBooking.dto.SignUpDto;
import com.hotel.RoomBooking.dto.UserDto;
import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.enums.UserRole;
import com.hotel.RoomBooking.exceptions.UserException;
import com.hotel.RoomBooking.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDto createUser(SignUpDto signUpRequest) throws UserException {

        try {

            Optional<Users> existingUser = userRepo.findByEmail(signUpRequest.getEmail());
            if(existingUser.isPresent()){
                throw new UserException("User with email exists "+ signUpRequest.getEmail());
            }
            Users newUser = new Users();
            newUser.setName(signUpRequest.getName());
            newUser.setEmail(signUpRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            newUser.setUserRole(UserRole.CUSTOMER);
            userRepo.save(newUser);
            System.out.println("Account Created successfully");

            return newUser.getUserDto();
        }
        catch (Exception e) {
            throw new UserException(e.toString());
        }


    }

/*    public UserDto login(LoginDto loginDto){
        try{

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}
