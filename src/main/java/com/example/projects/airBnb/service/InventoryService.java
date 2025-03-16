package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.HotelPriceDto;
import com.example.projects.airBnb.dto.HotelSearchRequest;
import com.example.projects.airBnb.dto.InventoryDto;
import com.example.projects.airBnb.dto.UpdateInventoryRequestDto;
import com.example.projects.airBnb.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
    List<InventoryDto> getAllInventoryByRoomId(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}
