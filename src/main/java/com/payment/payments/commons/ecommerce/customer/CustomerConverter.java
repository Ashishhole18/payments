package com.payment.payments.commons.ecommerce.customer;

import com.payment.payments.entity.Employee;
import com.payment.payments.entity.ecommerce.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerConverter {

    public Customer convertToCustomer(final CustomerResource customer) {
        return Customer.builder()
                .address(customer.getAddress())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
