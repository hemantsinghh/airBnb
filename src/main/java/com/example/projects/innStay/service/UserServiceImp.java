package com.example.projects.innStay.service;

import com.example.projects.innStay.dto.ProfileUpdateRequestDto;
import com.example.projects.innStay.entity.User;
import com.example.projects.innStay.exception.ResourceNotFoundException;
import com.example.projects.innStay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.projects.innStay.util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id:"+id));
    }

    @Override
    public void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto) {
        User user = getCurrentUser();
        if(profileUpdateRequestDto.getDateOfBirth()!=null){
            user.setDateOfBirth(profileUpdateRequestDto.getDateOfBirth());
        }
        if(profileUpdateRequestDto.getGender()!=null){
            user.setGender(profileUpdateRequestDto.getGender());
        }
        if(profileUpdateRequestDto.getName()!=null){
            user.setName(profileUpdateRequestDto.getName());
        }

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email:" + username));
    }
}
