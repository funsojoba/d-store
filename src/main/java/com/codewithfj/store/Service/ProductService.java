package com.codewithfj.store.Service;


import com.codewithfj.store.Dto.ApiResponse;
import com.codewithfj.store.Dto.CategoryResponse;
import com.codewithfj.store.Dto.ProductResponse;
import com.codewithfj.store.Entity.Product;
import com.codewithfj.store.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ApiResponse<List<ProductResponse>> listAll(
            Long categoryId
    ) {
        List<Product> products;
        if(categoryId == null){
            products = productRepository.findAll();
        }else{
            products = productRepository.findByCategoryId(categoryId);
        }

        List<ProductResponse> productResponse = products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .category(CategoryResponse.builder()
                                .id(product.getCategory().getId())
                                .name(product.getCategory().getName())
                                .build())
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
