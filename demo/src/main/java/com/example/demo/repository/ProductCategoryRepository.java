package com.example.demo.repository;

import com.example.demo.model.ProductCategory; // Εισαγωγή της οντότητας
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    // Μπορείτε να προσθέσετε custom queries αν χρειάζεται
}
