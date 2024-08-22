package com.payment.payments.user;

import lombok.*;

@Data
@Builder
public class UserResource {
    private String username;
    private String password;
    private String role;
    private String jwtToken;
}
