package com.easybytes.easystore.controller;

import com.easybytes.easystore.dto.OrderResponseDto;
import com.easybytes.easystore.service.IContactService;
import com.easybytes.easystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IOrderService iOrderService;
    private final IContactService iContactService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getAllPendingOrders() {
        List<OrderResponseDto> orders = iOrderService.getAllPendingOrders();
        return ResponseEntity.ok(orders);
    }
}
