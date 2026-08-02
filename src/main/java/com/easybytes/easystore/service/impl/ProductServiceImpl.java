package com.easybytes.easystore.service.impl;

import com.easybytes.easystore.entity.Product;
import com.easybytes.easystore.repository.ProductRepository;
import com.easybytes.easystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
