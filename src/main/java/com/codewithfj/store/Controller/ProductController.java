package com.codewithfj.store.Controller;


import com.codewithfj.store.Dto.ApiResponse;
import com.codewithfj.store.Dto.ProductResponse;
import com.codewithfj.store.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
