package com.easybytes.easystore.controller;

import com.easybytes.easystore.dto.OrderRequestDto;
import com.easybytes.easystore.dto.OrderResponseDto;
import com.easybytes.easystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService iOrderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto requestDto) {
        iOrderService.createOrder(requestDto);
        return ResponseEntity.ok("Order created successfully!");
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> loadCustomerOrders() {
        List<OrderResponseDto> orders = iOrderService.getCustomerOrders();
        return ResponseEntity.ok(orders);
    }
}
