package com.payment.payments.service.ecommerce;

import com.payment.payments.commons.ecommerce.category.CategoryRequest;
import com.payment.payments.commons.ecommerce.category.CategoryResource;
import com.payment.payments.entity.ecommerce.Category;
import com.payment.payments.repository.ecommerce.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResource saveCategory(final CategoryRequest categoryRequest) {
        Category category1 = Category.builder().name(categoryRequest.getName()).build();
        Category category = this.categoryRepository.save(category1);
        return CategoryResource.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .build();
    }
}
