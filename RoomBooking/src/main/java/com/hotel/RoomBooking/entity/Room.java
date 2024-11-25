package com.hotel.RoomBooking.entity;

import com.hotel.RoomBooking.dto.RoomDto;
import com.hotel.RoomBooking.enums.RoomType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private Long price;

    private Boolean available;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public RoomDto transformRoomToRoomDto(){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(this.getId());
        roomDto.setName(this.getName());
        roomDto.setRoomType(this.getRoomType());
        roomDto.setPrice(this.getPrice());
        roomDto.setAvailable(this.getAvailable());
        roomDto.setCreatedOn(this.getCreatedOn());
        roomDto.setUpdatedOn(this.getUpdatedOn());

        return roomDto;
    }
}
