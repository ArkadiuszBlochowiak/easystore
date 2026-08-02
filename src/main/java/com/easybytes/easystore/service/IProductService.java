package com.easybytes.easystore.service;

import com.easybytes.easystore.dto.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts();
}
