package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.OrderRequestDto;
import com.easybytes.easystore.dto.OrderResponseDto;
import com.easybytes.easystore.entity.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequest);

    List<OrderResponseDto> getCustomerOrders();

    List<OrderResponseDto> getAllPendingOrders();

    Order updateOrderStatus(Long orderId, String status);
}
