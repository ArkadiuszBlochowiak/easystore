package com.easybytes.easystore.repository;

import com.easybytes.easystore.entity.Customer;
import com.easybytes.easystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

    List<Order> findByOrderStatus(String orderStatus);
}
