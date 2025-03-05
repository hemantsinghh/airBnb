package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.HotelDto;
import com.example.projects.airBnb.entity.Hotel;
import com.example.projects.airBnb.entity.Room;
import com.example.projects.airBnb.exception.ResourceNotFoundException;
import com.example.projects.airBnb.repository.HotelRepository;
import com.example.projects.airBnb.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImp implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;


    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new Hotel with name: {}",hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto,Hotel.class); // will convert all the fields from hotelDto to hotel
        hotel.setActive(false);
        Hotel saveHotel = hotelRepository.save(hotel);
        log.info("Created a new Hotel with ID: {}", hotelDto.getId());
        return modelMapper.map(saveHotel,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel by ID: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating the hotel by ID:{}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Hotel not found");
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found"));
        for(Room room: hotel.getRooms()){
            inventoryService.deleteFutureInventories(room);
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found"));
        hotel.setActive(true);
        hotelRepository.save(hotel);
        System.out.println("Namaste");
        for(Room room: hotel.getRooms()){
            System.out.println("In cllop");
            inventoryService.initializeRoomForAYear(room);
        }
    }


}
