package com.payment.payments.commons.ecommerce.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryConverter {
    public CategoryRequest convertToRequest(CategoryResource categoryResource) {
        return CategoryRequest.builder()
                .categoryId(categoryResource.getCategoryId())
                .name(categoryResource.getName())
                .build();
    }
}
