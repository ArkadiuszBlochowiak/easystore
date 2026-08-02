package com.easybytes.easystore.service.impl;

import com.easybytes.easystore.dto.ProductDto;
import com.easybytes.easystore.entity.Product;
import com.easybytes.easystore.repository.ProductRepository;
import com.easybytes.easystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(this::transformToDto).collect(Collectors.toList());
    }

    private ProductDto transformToDto (Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
