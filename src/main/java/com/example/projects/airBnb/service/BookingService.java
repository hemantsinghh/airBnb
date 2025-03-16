package com.example.projects.airBnb.service;

import com.example.projects.airBnb.dto.BookingDto;
import com.example.projects.airBnb.dto.BookingRequest;
import com.example.projects.airBnb.dto.GuestDto;
import com.example.projects.airBnb.dto.UserDto;
import com.stripe.model.Event;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingByHotelId(Long hotelId);

    List<BookingDto> getMyBookings();

    UserDto getMyProfile();
}
