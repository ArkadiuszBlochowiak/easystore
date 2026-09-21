package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.OrderRequestDto;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequest);
}
