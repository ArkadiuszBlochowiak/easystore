package com.easybytes.easystore.service.impl;

import com.easybytes.easystore.constants.ApplicationConstants;
import com.easybytes.easystore.dto.OrderRequestDto;
import com.easybytes.easystore.entity.Customer;
import com.easybytes.easystore.entity.Order;
import com.easybytes.easystore.entity.OrderItem;
import com.easybytes.easystore.entity.Product;
import com.easybytes.easystore.exception.ResourceNotFoundException;
import com.easybytes.easystore.repository.CustomerRepository;
import com.easybytes.easystore.repository.OrderRepository;
import com.easybytes.easystore.repository.ProductRepository;
import com.easybytes.easystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createOrder(OrderRequestDto orderRequest) {
        Customer customer = getAuthenticatedCustomer();

        Order order = new Order();
        order.setCustomer(customer);
        BeanUtils.copyProperties(orderRequest, order);
        order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);

        List<OrderItem> orderItems = orderRequest.items().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product", "ProductID", item.productId().toString()
                    ));
            orderItem.setProduct(product);
            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(item.price());
            return orderItem;
        }).toList();

        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    private Customer getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
