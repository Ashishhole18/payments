package com.payment.payments.service.ecommerce;

import com.payment.payments.security.jwt.JwtUtils;
import com.payment.payments.commons.ecommerce.user.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;
    private  final UserDetailsService userDetailsService;
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jdbcUserDetailsManager = (JdbcUserDetailsManager) userDetailsService;
    }

    public boolean addUser(final UserResource userResource) {
        boolean isUserExist =  jdbcUserDetailsManager.userExists(userResource.getUsername());
        if(!isUserExist) {
            UserDetails userDetails = User.builder()
                    .username(bCryptPasswordEncoder.encode(userResource.getUsername()))
                    .password(bCryptPasswordEncoder.encode(userResource.getPassword()))
                    .roles("USER").build();
            this.jdbcUserDetailsManager.createUser(userDetails);
        }
        return false;
    }

    public boolean deleteUser(final UserResource userResource) {
        if(isUserExist(userResource.getUsername())) {
            this.jdbcUserDetailsManager.deleteUser(userResource.getUsername());
            return true;
        }
        return false;

    }
    public UserResource signinUser(final UserResource userResource) {
        if (isUserExist(userResource.getUsername())) {
            final Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userResource.getUsername(), userResource.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwtToken = this.jwtUtils.generateJwtToken(userDetails.getUsername());
            return UserResource.builder().jwtToken(jwtToken)
                    .role(userDetails.getAuthorities().toString())
                    .build();
        }
        return null;
    }

    private boolean isUserExist(final String username) {
        return this.jdbcUserDetailsManager.userExists(username);
    }
}
