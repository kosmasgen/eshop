package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Διορθωμένο query για την αναζήτηση παραγγελιών με το όνομα του προϊόντος
    List<Order> findByProductProductNameContainingIgnoreCase(String productName);
}


