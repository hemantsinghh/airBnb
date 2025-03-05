package com.example.projects.airBnb.repository;

import com.example.projects.airBnb.entity.Inventory;
import com.example.projects.airBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByRoomAndDateAfter(Room room, LocalDate date);
}
