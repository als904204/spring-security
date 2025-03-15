package com.easybytes.springsecsection5.controller;

import com.easybytes.springsecsection5.model.Customer;
import com.easybytes.springsecsection5.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        String hashPwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashPwd);
        Customer newCustomer = customerRepository.save(customer);
        return ResponseEntity.ok("User " + newCustomer.getEmail() + " registered successfully");
    }
}
