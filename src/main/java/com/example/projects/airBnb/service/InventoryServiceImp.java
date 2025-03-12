package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.HotelDto;
import com.example.projects.airBnb.dto.HotelPriceDto;
import com.example.projects.airBnb.dto.HotelSearchRequest;
import com.example.projects.airBnb.entity.Hotel;
import com.example.projects.airBnb.entity.HotelMinPrice;
import com.example.projects.airBnb.entity.Inventory;
import com.example.projects.airBnb.entity.Room;
import com.example.projects.airBnb.repository.HotelMinPriceRepository;
import com.example.projects.airBnb.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImp implements InventoryService {

    public final InventoryRepository inventoryRepository;
    public final ModelMapper modelMapper;
    public final HotelMinPriceRepository hotelMinPriceRepository;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate  = today.plusYears(1);
        for(;!today.isAfter(endDate); today = today.plusDays(1)){
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .reservedCount(0)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteFutureInventories(Room room) {
        log.info("Deleting the inventories for room with id: {}", room.getId());
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("Searching hotels for {} city, from {} to {}", hotelSearchRequest.getCity(),hotelSearchRequest.getStartDate(),hotelSearchRequest.getEndDate());
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());
        long dateCount = ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(),hotelSearchRequest.getEndDate())+1;
        Page<HotelPriceDto> hotelPage = hotelMinPriceRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate(),
                hotelSearchRequest.getRoomsCount(),
                dateCount, pageable);
        return hotelPage;
    }
}
