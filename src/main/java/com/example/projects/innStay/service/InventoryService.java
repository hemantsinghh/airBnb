package com.example.projects.innStay.service;

import com.example.projects.innStay.dto.HotelPriceDto;
import com.example.projects.innStay.dto.HotelSearchRequest;
import com.example.projects.innStay.dto.InventoryDto;
import com.example.projects.innStay.dto.UpdateInventoryRequestDto;
import com.example.projects.innStay.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
    List<InventoryDto> getAllInventoryByRoomId(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}
