package com.easybytes.easystore.repository;

import com.easybytes.easystore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional, because we extend JpaRepository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
