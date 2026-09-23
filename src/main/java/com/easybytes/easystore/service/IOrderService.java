package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.OrderRequestDto;
import com.easybytes.easystore.dto.OrderResponseDto;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequest);

    List<OrderResponseDto> getCustomerOrders();
}
