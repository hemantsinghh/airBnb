package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.ProfileUpdateRequestDto;
import com.example.projects.airBnb.entity.User;

public interface UserService {
    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);
}
