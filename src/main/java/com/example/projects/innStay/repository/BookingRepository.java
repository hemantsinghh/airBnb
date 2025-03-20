package com.example.projects.innStay.repository;

import com.example.projects.innStay.entity.Booking;
import com.example.projects.innStay.entity.Hotel;
import com.example.projects.innStay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByPaymentSessionId(String sessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByUser(User user);
}
