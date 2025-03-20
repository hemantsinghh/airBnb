package com.example.projects.innStay.security;

import com.example.projects.innStay.dto.LoginDto;
import com.example.projects.innStay.dto.SignUpRequestDto;
import com.example.projects.innStay.dto.UserDto;
import com.example.projects.innStay.entity.User;
import com.example.projects.innStay.entity.enums.Role;
import com.example.projects.innStay.exception.ResourceNotFoundException;
import com.example.projects.innStay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto){
        User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);
        if(user!=null){
            throw new RuntimeException("User is already present with same email id");
        }
        User newUser = modelMapper.map(signUpRequestDto, User.class);
        newUser.setRoles(Set.of(Role.GUEST));
        newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        newUser = userRepository.save(newUser);

        return modelMapper.map(newUser, UserDto.class);
    }

    public String[] login(LoginDto loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()
            ));
            User user = (User) authentication.getPrincipal();
            String[] arr = new String[2];
            arr[0] = jwtService.generateAccessToken(user);
            arr[1] = jwtService.generateRefreshToken(user);
            return arr;
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("User does not exist");
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    public String refreshToken(String refreshToken){
        Long id = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not Found"));

        return jwtService.generateAccessToken(user);
    }
}
