package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.mapper.OrderMapper;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    // Δημιουργία παραγγελίας με το ID του προϊόντος και την ποσότητα

    @Override
    public OrderDTO createOrderWithProductAndQuantity(int productId, int quantity) {
        // Έλεγχος εγκυρότητας εισόδου
        if (productId <= 0) {
            throw new IllegalArgumentException("Το productId πρέπει να είναι μεγαλύτερο από το μηδέν.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Η ποσότητα πρέπει να είναι μεγαλύτερη από το μηδέν.");
        }

        // Αναζήτηση προϊόντος στη βάση δεδομένων
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Το προϊόν με ID " + productId + " δεν βρέθηκε."));

        // Δημιουργία παραγγελίας
        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setPrice(product.getPrice()); // Τιμή ανά μονάδα από το προϊόν
        order.setTotalPrice(quantity * product.getPrice()); // Υπολογισμός συνολικής τιμής
        order.setSupplier(product.getSupplier()); // Ο προμηθευτής συνδέεται από το προϊόν

        // Αποθήκευση παραγγελίας στη βάση δεδομένων
        Order savedOrder = orderRepository.save(order);

        // Μετατροπή σε DTO και επιστροφή
        return orderMapper.toDTO(savedOrder);
    }


    // Εύρεση παραγγελίας με βάση το ID
    @Override
    public OrderDTO getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Η παραγγελία με ID " + id + " δεν βρέθηκε."));
        return orderMapper.toDTO(order);
    }

    // Ενημέρωση παραγγελίας
    @Override
    public OrderDTO updateOrder(int id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Η παραγγελία με ID " + id + " δεν βρέθηκε."));

        orderMapper.toEntity(orderDTO, existingOrder);
        existingOrder.setTotalPrice(existingOrder.getQuantity() * existingOrder.getPrice());

        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDTO(updatedOrder);
    }

    // Διαγραφή παραγγελίας
    @Override
    public void deleteOrder(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Η παραγγελία με ID " + id + " δεν βρέθηκε."));
        orderRepository.delete(order);
    }

    // Αναζήτηση παραγγελιών με βάση το όνομα προϊόντος
    @Override
    public List<OrderDTO> searchOrders(String productName) {
        List<Order> orders = orderRepository.findByProductProductNameContainingIgnoreCase(productName);
        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Επιστροφή όλων των παραγγελιών
    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
