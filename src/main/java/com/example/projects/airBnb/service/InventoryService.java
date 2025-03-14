package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.HotelPriceDto;
import com.example.projects.airBnb.dto.HotelSearchRequest;
import com.example.projects.airBnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
