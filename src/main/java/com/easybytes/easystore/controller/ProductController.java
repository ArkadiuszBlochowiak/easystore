package com.easybytes.easystore.controller;

import com.easybytes.easystore.dto.ProductDto;
import com.easybytes.easystore.entity.Product;
import com.easybytes.easystore.repository.ProductRepository;
import com.easybytes.easystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return iProductService.getProducts();
    }
}
