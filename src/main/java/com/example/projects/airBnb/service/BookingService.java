package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.BookingDto;
import com.example.projects.airBnb.dto.BookingRequest;
import com.example.projects.airBnb.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
