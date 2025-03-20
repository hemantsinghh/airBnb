package com.example.projects.innStay.service;

import com.example.projects.innStay.dto.HotelPriceDto;
import com.example.projects.innStay.dto.HotelSearchRequest;
import com.example.projects.innStay.dto.InventoryDto;
import com.example.projects.innStay.dto.UpdateInventoryRequestDto;
import com.example.projects.innStay.entity.Inventory;
import com.example.projects.innStay.entity.Room;
import com.example.projects.innStay.entity.User;
import com.example.projects.innStay.exception.ResourceNotFoundException;
import com.example.projects.innStay.repository.HotelMinPriceRepository;
import com.example.projects.innStay.repository.InventoryRepository;
import com.example.projects.innStay.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.projects.innStay.util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImp implements InventoryService {

    public final InventoryRepository inventoryRepository;
    public final ModelMapper modelMapper;
    public final HotelMinPriceRepository hotelMinPriceRepository;
    public final RoomRepository roomRepository;

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

    @Override
    public List<InventoryDto> getAllInventoryByRoomId(Long roomId) {
        log.info("Getting all inventory by room for room with id:{}",roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with id:"+roomId));
        User user = getCurrentUser();
        if(!user.equals(room.getHotel().getOwner())) throw new AccessDeniedException("You are not the owner of the room with ID:"+roomId);
        return inventoryRepository.findByRoomOrderByDate(room).stream()
                .map((element) -> modelMapper.map(element, InventoryDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with id:"+roomId));
        User user = getCurrentUser();
        if(!user.equals(room.getHotel().getOwner())) throw new AccessDeniedException("You are not the owner of the room with ID:"+roomId);

        inventoryRepository.getInventoryAndLockBeforeUpdate(roomId,
                updateInventoryRequestDto.getStartDate(),
                updateInventoryRequestDto.getEndDate());

        inventoryRepository.updateInventory(roomId, updateInventoryRequestDto.getStartDate(),
                updateInventoryRequestDto.getEndDate()
                ,updateInventoryRequestDto.getClosed(),
                updateInventoryRequestDto.getSurgeFactor());
    }
}
