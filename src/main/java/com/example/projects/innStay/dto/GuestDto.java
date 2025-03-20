package com.example.projects.innStay.dto;

import com.example.projects.innStay.entity.User;
import com.example.projects.innStay.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {

    private User user;
    private String name;
    private Gender gender;
    private Integer age;
//    private Set<Booking> bookings;
}
