package com.payment.payments.repository.ecommerce;

import com.payment.payments.entity.ecommerce.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
