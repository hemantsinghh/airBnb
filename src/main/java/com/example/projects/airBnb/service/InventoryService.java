package com.example.projects.airBnb.service;

import com.example.projects.airBnb.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);

}
