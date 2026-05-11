package com.codewithfj.store.Service;

import com.codewithfj.store.Dto.ApiResponse;
import com.codewithfj.store.Dto.CategoryRequest;
import com.codewithfj.store.Dto.CategoryResponse;
import com.codewithfj.store.Dto.ProductResponse;
import com.codewithfj.store.Entity.Category;
import com.codewithfj.store.Entity.Product;
import com.codewithfj.store.Exception.ResourceNotFoundException;
import com.codewithfj.store.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ApiResponse<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        var categoryName = categoryRequest.getName();

        if(categoryRepository.findByName(categoryName).isPresent()) {
            throw new IllegalArgumentException("Category name already exists");
        }
        Category category = Category.builder().name(categoryName).build();
        categoryRepository.save(category);
        return ApiResponse.<CategoryResponse>builder()
                .success(true)
                .message("Category created successfully")
                .build();
    }

    public ApiResponse<List<CategoryResponse>> listAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryResponse> categoryResponses = categories.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()
                ).toList();

        return ApiResponse.<List<CategoryResponse>>builder()
                .success(true)
                .data(categoryResponses)
                .message("Categories retrieved successfully")
                .build();

    }
}
