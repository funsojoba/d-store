package com.codewithfj.store.Service;


import com.codewithfj.store.Dto.ApiResponse;
import com.codewithfj.store.Dto.CategoryResponse;
import com.codewithfj.store.Dto.ProductRequest;
import com.codewithfj.store.Dto.ProductResponse;
import com.codewithfj.store.Entity.Category;
import com.codewithfj.store.Entity.Product;
import com.codewithfj.store.Exception.ResourceNotFoundException;
import com.codewithfj.store.Repository.CategoryRepository;
import com.codewithfj.store.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ApiResponse<ProductResponse> findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .imageUrl(product.getImageUrl())
                    .isActive(product.getIsActive())
                    .category(CategoryResponse.builder()
                            .id(product.getCategory().getId())
                            .name(product.getCategory().getName())
                            .build())
                    .createdAt(product.getCreatedAt())
                    .build();

            return ApiResponse.<ProductResponse>builder()
                    .success(true)
                    .data(productResponse)
                    .message("Product fetched successfully")
                    .build();

    };

    public ApiResponse<ProductResponse> createProduct(ProductRequest productRequest) {
        if (productRepository.findByName(productRequest.getName()).isPresent()) {
            throw new ResourceNotFoundException("Product already exists");
        }
        Category category = null;

        if(productRequest.getCategory() != null){
            category = categoryRepository.findById(productRequest.getCategory()).orElseThrow(
                    () -> new ResourceNotFoundException("Category not found")
            );
        }

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .rating(0L)
                .imageUrl(productRequest.getImageUrl())
                .isActive(productRequest.getIsActive())
                .category(category)
                .build();
        productRepository.save(product);

        assert category != null;
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .category(CategoryResponse.builder()
                                        .id(category.getId())
                                        .name(category.getName())
                                        .build())
                .isActive(product.getIsActive())
                .imageUrl(productRequest.getImageUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

        return ApiResponse.<ProductResponse>builder()
                .success(true)
                .data(productResponse)
                .message("Product created successfully")
                .build();

    }

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
                        .imageUrl(product.getImageUrl())
                        .isActive(product.getIsActive())
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
