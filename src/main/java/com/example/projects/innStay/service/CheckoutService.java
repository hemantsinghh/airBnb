package com.example.projects.innStay.service;

import com.example.projects.innStay.entity.Booking;

public interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}
