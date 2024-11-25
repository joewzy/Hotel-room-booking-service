package com.hotel.RoomBooking.service.admin.room;

import com.hotel.RoomBooking.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
}
