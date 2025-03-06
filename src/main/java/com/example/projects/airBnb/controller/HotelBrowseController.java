package com.example.projects.airBnb.controller;


import com.example.projects.airBnb.dto.HotelDto;
import com.example.projects.airBnb.dto.HotelSearchRequest;
import com.example.projects.airBnb.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
@AllArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelDto> page =  inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }
}
