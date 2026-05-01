package com.codewithfj.store.Service;


import com.codewithfj.store.Dto.ApiResponse;
import com.codewithfj.store.Dto.ProductResponse;
import com.codewithfj.store.Entity.Product;
import com.codewithfj.store.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ApiResponse<List<ProductResponse>> listAll() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponse = products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .category(product.getCategory())
                        .createdAt(product.getCreatedAt())
                        .build()
                ).toList();
        return ApiResponse.<List<ProductResponse>>builder()
                .success(true)
                .data(productResponse)
                .message("Success")
                .build();
    }
}
