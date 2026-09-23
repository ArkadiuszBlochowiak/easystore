package com.easybytes.easystore.controller;

import com.easybytes.easystore.constants.ApplicationConstants;
import com.easybytes.easystore.dto.OrderResponseDto;
import com.easybytes.easystore.dto.ResponseDto;
import com.easybytes.easystore.entity.Order;
import com.easybytes.easystore.service.IContactService;
import com.easybytes.easystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/orders/{orderId}/confirm")
    public ResponseEntity<ResponseDto> confirmOrder(@PathVariable Long orderId) {
        Order confirmedOrder = iOrderService.updateOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CONFIRMED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + confirmedOrder.getOrderId() + " has been approved.")
        );
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable Long orderId) {
        Order cancelledOrder = iOrderService.updateOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CANCELLED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + cancelledOrder.getOrderId() + " has been cancelled.")
        );
    }
}
