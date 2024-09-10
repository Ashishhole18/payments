package com.payment.payments.service.ecommerce;

import com.payment.payments.commons.ecommerce.customer.CustomerConverter;
import com.payment.payments.commons.ecommerce.customer.CustomerResource;
import com.payment.payments.commons.ecommerce.user.UserResourceConverter;
import com.payment.payments.entity.ecommerce.Customer;
import com.payment.payments.repository.ecommerce.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    final private UserService userService;
    final private CustomerRepository customerRepository;
    final private UserResourceConverter userResourceConverter;
    final private CustomerConverter customerConverter;

    @Autowired
    public CustomerService(UserService userService, CustomerRepository customerRepository, UserResourceConverter userResourceConverter, CustomerConverter customerConverter) {
        this.userService = userService;
        this.customerRepository = customerRepository;
        this.userResourceConverter = userResourceConverter;
        this.customerConverter = customerConverter;
    }

    public String addUser(final CustomerResource customerResource) {

        final boolean isUserRegistered = this.userService.addUser(this.userResourceConverter.convertToUserResource(customerResource));
        if(isUserRegistered) {
            final Customer customer = this.customerConverter.convertToCustomer(customerResource);
            final Customer savedCustomer = this.customerRepository.save(customer);
            return savedCustomer.getFirstName() + " " +savedCustomer.getLastName() + " Registered Successfully";
        }
        else
            return "something bad happened";
    }
}
