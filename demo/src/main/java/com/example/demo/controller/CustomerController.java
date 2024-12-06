package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers") // Η βάση για όλα τα endpoints της κλάσης
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Δημιουργία νέου πελάτη (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Επιστρέφει HTTP Status 201 όταν δημιουργείται ένας πελάτης
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    // Ανάκτηση όλων των πελατών (GET)
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Ανάκτηση πελάτη με βάση το ID (GET)
    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    // Ενημέρωση πελάτη με βάση το ID (PUT)
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    // Διαγραφή πελάτη με βάση το ID (DELETE)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Επιστρέφει HTTP Status 204 για επιτυχημένη διαγραφή
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }

    // Ανάκτηση πελάτη με βάση παράμετρο (GET με @RequestParam)
    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam String name) {
        return customerService.searchCustomers(name);
    }
}









