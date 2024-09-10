package com.payment.payments.commons.ecommerce.user;

import lombok.*;

@Data
@Builder
public class UserResource {
    private String username;
    private String password;
    private String role;
    private String jwtToken;
}
