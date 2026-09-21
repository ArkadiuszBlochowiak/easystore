package com.easybytes.easystore.controller;

import com.easybytes.easystore.dto.PaymentIntentRequestDto;
import com.easybytes.easystore.dto.PaymentIntentResponseDto;
import com.easybytes.easystore.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final IPaymentService iPaymentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentIntentResponseDto> createPaymentIntent(@RequestBody PaymentIntentRequestDto paymentRequest) {
        PaymentIntentResponseDto response = iPaymentService.createPayment(paymentRequest);
        return ResponseEntity.ok(response);
    }
}
