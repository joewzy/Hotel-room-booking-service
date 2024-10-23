package com.hotel.RoomBooking.service.auth;

import com.hotel.RoomBooking.entity.Users;
import com.hotel.RoomBooking.enums.UserRole;
import com.hotel.RoomBooking.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final UserRepo userRepo;

    //create default admin if none exists
    // this is run when application starts

    @PostConstruct
    public void createAdminAccount(){
        Optional<Users> adminUser = userRepo.findByUserRole(UserRole.ADMIN);
        if(adminUser.isEmpty()){
            Users defaultAdminAcc = new Users();
            defaultAdminAcc.setName("admin 1");
            defaultAdminAcc.setEmail("admin@admin.com");
            defaultAdminAcc.setPassword(new BCryptPasswordEncoder().encode("admin"));
            defaultAdminAcc.setUserRole(UserRole.ADMIN);
            userRepo.save(defaultAdminAcc);
            System.out.println("Default Admin Account Created successfully");
        }
        else {
            System.out.println("Default admin account exists");
        }
    }
}
