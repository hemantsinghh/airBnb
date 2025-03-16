package com.example.projects.airBnb.service;

import com.example.projects.airBnb.entity.Booking;

public interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}
