package com.example.projects.airBnb.controller;


import com.example.projects.airBnb.dto.HotelDto;
import com.example.projects.airBnb.dto.HotelInfoDto;
import com.example.projects.airBnb.dto.HotelPriceDto;
import com.example.projects.airBnb.dto.HotelSearchRequest;
import com.example.projects.airBnb.service.HotelService;
import com.example.projects.airBnb.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@AllArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelPriceDto> page =  inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
