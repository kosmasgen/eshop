package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerImpl implements CustomerService {

    private final CustomerRepository customerRepository; // Το repository για την πρόσβαση στη βάση
    private final CustomerMapper customerMapper; // Το mapper για μετατροπές DTO <-> Entity

    // Κατασκευαστής με dependency injection
    @Autowired
    public CustomerImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    // CREATE
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        // Μετατροπή DTO -> Entity
        Customer customer = customerMapper.toEntity(customerDTO);
        // Αποθήκευση στη βάση
        Customer savedCustomer = customerRepository.save(customer);
        // Επιστροφή ως DTO
        return customerMapper.toDTO(savedCustomer);
    }

    // READ ALL
    @Override
    public List<CustomerDTO> getAllCustomers() {
        // Εύρεση όλων των πελατών
        List<Customer> customers = customerRepository.findAll();
        // Μετατροπή σε λίστα DTO
        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Override
    public CustomerDTO getCustomerById(int id) {
        // Εύρεση πελάτη βάσει ID
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
        // Επιστροφή ως DTO
        return customerMapper.toDTO(customer);
    }

    // UPDATE
    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        // Εύρεση υπάρχοντος πελάτη
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        // Ενημέρωση πεδίων με ModelMapper
        customerMapper.toEntity(customerDTO); // Μηχανισμός αυτόματης χαρτογράφησης

        // Αποθήκευση ενημερωμένου πελάτη
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        // Επιστροφή ως DTO
        return customerMapper.toDTO(updatedCustomer);
    }


    // DELETE
    @Override
    public void deleteCustomer(int id) {
        // Έλεγχος αν ο πελάτης υπάρχει
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
        // Διαγραφή πελάτη
        customerRepository.deleteById(id);
    }

    // SEARCH - Αναζήτηση πελατών με βάση το όνομα
    @Override
    public List<CustomerDTO> searchCustomers(String name) {
        // Αναζητά πελάτες με βάση το όνομα
        List<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCase(name);
        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
