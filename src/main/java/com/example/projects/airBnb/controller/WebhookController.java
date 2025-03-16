package com.example.projects.airBnb.controller;


import com.example.projects.airBnb.service.BookingService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final BookingService bookingService;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping("/payment")
    public ResponseEntity<Void> capturePayments(@RequestBody String payload, @RequestHeader("Stripe-Signature") String signatureHeader){
        try {
            Event event = Webhook.constructEvent(payload,signatureHeader, endpointSecret);
            bookingService.capturePayment(event);
            return ResponseEntity.noContent().build();
        }catch (SignatureVerificationException e){
            throw new RuntimeException(e);
        }
    }
}
