package com.payment.payments.commons.ecommerce.user;

import com.payment.payments.commons.ecommerce.customer.CustomerResource;
import com.payment.payments.employee.EmployeeResource;
import org.springframework.stereotype.Service;

@Service
public class UserResourceConverter {

    public UserResource convertToUserResource(final CustomerResource employeeResource) {
        return UserResource.builder()
                .username(employeeResource.getUserName())
                .password(employeeResource.getPassword())
                .role(UserRole.ROLE_CUSTOMER)
                .build();
    }
}
