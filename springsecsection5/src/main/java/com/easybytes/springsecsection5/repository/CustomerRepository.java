package com.easybytes.springsecsection5.repository;

import com.easybytes.springsecsection5.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}
