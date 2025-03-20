package com.example.projects.innStay.repository;

import com.example.projects.innStay.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
