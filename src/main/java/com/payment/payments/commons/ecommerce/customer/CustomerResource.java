package com.payment.payments.commons.ecommerce.customer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class CustomerResource {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String userName;
    private String password;
}
