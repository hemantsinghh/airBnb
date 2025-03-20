package com.example.projects.innStay.service;

import com.example.projects.innStay.dto.BookingDto;
import com.example.projects.innStay.dto.BookingRequest;
import com.example.projects.innStay.dto.GuestDto;
import com.example.projects.innStay.dto.UserDto;
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
