package com.payment.payments.rest.user;

import com.payment.payments.security.jwt.JwtUtils;
import com.payment.payments.commons.ecommerce.user.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"user"})
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    private  final UserDetailsService userDetailsService;
    private  JdbcUserDetailsManager jdbcUserDetailsManager;

    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jdbcUserDetailsManager = (JdbcUserDetailsManager) this.userDetailsService;
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity createUser(@RequestBody UserResource userResource) {
        UserDetails userDetails = User.builder()
                .username(userResource.getUsername())
                .password(bCryptPasswordEncoder.encode(userResource.getPassword()))
                .roles("USER").build();
        this.jdbcUserDetailsManager.createUser(userDetails);

        return new ResponseEntity("User " + userResource.getUsername() + " Has been created Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete-user")
    public ResponseEntity deleteUser(@RequestBody UserResource userResource) {

        this.jdbcUserDetailsManager.deleteUser(userResource.getUsername());
        return new ResponseEntity<>("User " + userResource.getUsername() + " Has been Successfully deleted", HttpStatus.OK);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity singInUser(@RequestBody UserResource userResource) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userResource.getUsername(), userResource.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String userName = userDetails.getUsername();
        String jwtToken = this.jwtUtils.generateJwtToken(userName);
        return new ResponseEntity(UserResource.builder().role(userDetails.getAuthorities().toString()).jwtToken(jwtToken).username(userName).build(), HttpStatus.OK);

    }
}
