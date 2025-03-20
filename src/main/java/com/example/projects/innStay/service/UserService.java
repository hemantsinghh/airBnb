package com.example.projects.innStay.service;

import com.example.projects.innStay.dto.ProfileUpdateRequestDto;
import com.example.projects.innStay.entity.User;

public interface UserService {
    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);
}
