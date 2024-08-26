package com.payment.payments.repository.ecommerce;

import com.payment.payments.entity.ecommerce.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
