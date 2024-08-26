package com.payment.payments.repository.ecommerce;

import com.payment.payments.entity.ecommerce.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
