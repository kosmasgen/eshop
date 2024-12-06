package com.example.demo.repository;

import com.example.demo.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    List<Supplier> findByFirstNameContainingIgnoreCase(String name);
    Optional<Supplier> findById(Integer id);
    List<Supplier> findByLocationContainingIgnoreCase(String location);
}
