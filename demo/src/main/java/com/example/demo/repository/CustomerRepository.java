package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


import com.example.demo.model.Customer; // Η οντότητα
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Προσθήκη μεθόδου για την αναζήτηση πελατών με βάση το όνομα
    List<Customer> findByFirstNameContainingIgnoreCase(String name);
}
