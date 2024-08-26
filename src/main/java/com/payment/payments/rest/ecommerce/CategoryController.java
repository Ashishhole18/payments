package com.payment.payments.rest.ecommerce;

import com.payment.payments.commons.ecommerce.category.CategoryConverter;
import com.payment.payments.commons.ecommerce.category.CategoryRequest;
import com.payment.payments.commons.ecommerce.category.CategoryResource;
import com.payment.payments.service.ecommerce.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryConverter categoryConverter) {
        this.categoryService = categoryService;
        this.categoryConverter = categoryConverter;
    }

    @PostMapping(value = "/add-category")
    public ResponseEntity addCategory(@RequestBody CategoryResource categoryResource) {
        CategoryRequest categoryRequest = this.categoryConverter.convertToRequest(categoryResource);
        CategoryResource categoryResponse = this.categoryService.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }
}
