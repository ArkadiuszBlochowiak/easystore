package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.PaymentIntentRequestDto;
import com.easybytes.easystore.dto.PaymentIntentResponseDto;

public interface IPaymentService {
    PaymentIntentResponseDto createPayment(PaymentIntentRequestDto requestDto);
}
