package com.payment.payments.rest.ecommerce;

import com.payment.payments.commons.ecommerce.customer.CustomerResource;
import com.payment.payments.service.ecommerce.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;

        @Autowired
    public CustomerController(final CustomerService customerService) {
            this.customerService = customerService;
        }

    @PostMapping(value = "/add-customer")
    public ResponseEntity addCustomer(final CustomerResource customerResource) {
            return ResponseEntity.ok(this.customerService.addUser(customerResource));
    }

}
