package com.example.projects.airBnb.dto;

import com.example.projects.airBnb.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String email;
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
}
