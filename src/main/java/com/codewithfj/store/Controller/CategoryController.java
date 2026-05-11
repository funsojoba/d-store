package com.codewithfj.store.Controller;


import com.codewithfj.store.Dto.ApiResponse;
import com.codewithfj.store.Dto.CategoryRequest;
import com.codewithfj.store.Dto.CategoryResponse;
import com.codewithfj.store.Dto.ProductResponse;
import com.codewithfj.store.Service.CategoryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;

    @PostMapping("/")
    public ApiResponse<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping("/")
    public ApiResponse<List<CategoryResponse>> findAll(){
        return categoryService.listAllCategories();
    }
}
