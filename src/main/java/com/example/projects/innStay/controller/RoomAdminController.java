package com.example.projects.innStay.controller;

import com.example.projects.innStay.dto.RoomDto;
import com.example.projects.innStay.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@AllArgsConstructor
public class RoomAdminController {
    final private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotelId,
                                                 @RequestBody RoomDto roomDto){
        RoomDto room = roomService.createNewRoom(hotelId, roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok(roomService.getAllRoomsInHotel(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomsInHotel(@PathVariable Long roomId){
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<RoomDto> deleteRoomById(@PathVariable Long roomId){
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
