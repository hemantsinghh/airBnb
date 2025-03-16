package com.example.projects.airBnb.controller;


import com.example.projects.airBnb.dto.BookingDto;
import com.example.projects.airBnb.dto.ProfileUpdateRequestDto;
import com.example.projects.airBnb.dto.UserDto;
import com.example.projects.airBnb.entity.User;
import com.example.projects.airBnb.service.BookingService;
import com.example.projects.airBnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileUpdateRequestDto profileUpdateRequestDto){
        userService.updateProfile(profileUpdateRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myBookings")
    public ResponseEntity<List<BookingDto>> getMyBookings(){
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(){
        return ResponseEntity.ok(bookingService.getMyProfile());
    }
}
