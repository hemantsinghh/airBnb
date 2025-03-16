package com.example.projects.airBnb.repository;

import com.example.projects.airBnb.entity.Hotel;
import com.example.projects.airBnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findByOwner(User user);
}
