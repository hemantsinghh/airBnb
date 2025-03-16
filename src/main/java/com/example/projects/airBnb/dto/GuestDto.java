package com.example.projects.airBnb.dto;

import com.example.projects.airBnb.entity.User;
import com.example.projects.airBnb.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {

    private User user;
    private String name;
    private Gender gender;
    private Integer age;
//    private Set<Booking> bookings;
}
