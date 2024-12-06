package com.example.demo.mapper;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component // Η κλάση σημειώνεται ως Spring Component, ώστε να είναι διαθέσιμη μέσω Dependency Injection.
public class CustomerMapper {

    // Ιδιωτικό πεδίο που κρατάει την αναφορά στο ModelMapper.
    private final ModelMapper modelMapper;

    public CustomerMapper() {
        this.modelMapper = new ModelMapper(); // Δημιουργεί μια νέα παρουσία του ModelMapper.
    }


    public CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class); // Χρησιμοποιεί το ModelMapper για τη χαρτογράφηση.
    }


    public Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class); // Χρησιμοποιεί το ModelMapper για τη χαρτογράφηση.
    }
    public  void  updateEntityFromDTO(CustomerDTO customerDTO, Customer customer) {
        if (customerDTO.getId() != null &&! customerDTO.getId().equals(customer.getId())) {
            throw new IllegalArgumentException("Δεν επιτρέπεται αλλαγή του ID.");
        }
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setTelephone(customerDTO.getTelephone());
        customer.setAfm(customerDTO.getAfm());
        customer.setWholesale(customerDTO.isWholesale());
        customer.setBalance(customerDTO.getBalance());
    }
}
