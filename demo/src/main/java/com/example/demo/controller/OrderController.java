package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Δημιουργία παραγγελίας
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        if (orderDTO.getProductId() <= 0) {
            throw new IllegalArgumentException("Το productId είναι υποχρεωτικό.");
        }
        if (orderDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Η ποσότητα πρέπει να είναι μεγαλύτερη από το μηδέν.");
        }
        return orderService.createOrderWithProductAndQuantity(orderDTO.getProductId(), orderDTO.getQuantity());
    }

    // Λήψη παραγγελίας με βάση το ID
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    // Ενημέρωση παραγγελίας
    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(id, orderDTO);
    }

    // Διαγραφή παραγγελίας με βάση το ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }

    // Αναζήτηση παραγγελιών με βάση το όνομα προϊόντος
    @GetMapping("/search")
    public List<OrderDTO> searchOrders(@RequestParam String productName) {
        return orderService.searchOrders(productName);
    }
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
}
