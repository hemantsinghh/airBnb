package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.RoomDto;
import com.example.projects.airBnb.entity.Hotel;
import com.example.projects.airBnb.entity.Room;
import com.example.projects.airBnb.exception.ResourceNotFoundException;
import com.example.projects.airBnb.repository.HotelRepository;
import com.example.projects.airBnb.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImp implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Create a new room in hotel with Id: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        roomRepository.save(room);
        //TODO: Create inventory as the hotel is active and room is created
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("getting all rooms");
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return hotel.getRooms()
                .stream()
                .map((element)->modelMapper.map(element,RoomDto.class)).collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the room with Id: {}", roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("deleting the room with Id: {}", roomId);
        boolean exists = roomRepository.existsById(roomId);
        if(!exists){
            throw new ResourceNotFoundException("Room not found with ID:"+roomId);
        }
        roomRepository.deleteById(roomId);//TODO: delete all future inventory for this room
    }
}
