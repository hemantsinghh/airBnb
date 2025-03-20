package com.example.projects.innStay.repository;

import com.example.projects.innStay.entity.Hotel;
import com.example.projects.innStay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findByOwner(User user);
}
