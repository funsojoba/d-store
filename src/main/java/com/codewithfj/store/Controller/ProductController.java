package com.codewithfj.store.Controller;


import com.codewithfj.store.Dto.ApiResponse;
import com.codewithfj.store.Dto.ProductResponse;
import com.codewithfj.store.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ApiResponse<List<ProductResponse>> product(
            @RequestParam(required = false) Long categoryId
    ) {
        return productService.listAll(categoryId);
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> findById(@PathVariable Long id){
        return productService.findById(id);
    }
}
