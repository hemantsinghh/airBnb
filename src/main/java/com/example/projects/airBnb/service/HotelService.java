package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.HotelDto;
import com.example.projects.airBnb.dto.HotelInfoDto;

import java.util.List;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotel(Long id);
    HotelInfoDto getHotelInfoById(Long hotelId);

    List<HotelDto> getAllHotels();
}
