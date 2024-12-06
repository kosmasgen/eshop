package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Έλεγχος αν υπάρχει ήδη πελάτης με το ίδιο AFM
        if (customerRepository.existsByAfm(customerDTO.getAfm())) {
            throw new RuntimeException("Το AFM υπάρχει ήδη στη βάση.");
        }

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
                .orElseThrow(() -> new RuntimeException("Ο πελάτης δεν βρέθηκε με το ID: " + id));
        // Επιστροφή ως DTO
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        // Εύρεση του υπάρχοντος πελάτη με το ID
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ο πελάτης δεν βρέθηκε με το ID: " + id));

        // Πριν ενημερώσουμε την οντότητα, εξασφαλίζουμε ότι το ID δεν αλλάζει
        // Αν το DTO περιλαμβάνει το ID, το αγνοούμε και το αφήνουμε στην υπάρχουσα οντότητα
        existingCustomer.setId(id);  // Ασφαλίζουμε ότι το ID παραμένει το ίδιο

        // Ενημέρωση της υπάρχουσας οντότητας με τα δεδομένα του DTO
        customerMapper.updateEntityFromDTO(customerDTO, existingCustomer);

        // Αποθήκευση του ενημερωμένου πελάτη
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        // Επιστροφή του ενημερωμένου πελάτη ως DTO
        return customerMapper.toDTO(updatedCustomer);
    }


    // DELETE
    @Override
    public void deleteCustomer(int id) {
        // Έλεγχος αν ο πελάτης υπάρχει
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Ο πελάτης δεν βρέθηκε με το ID: " + id);
        }
        // Διαγραφή πελάτη
        customerRepository.deleteById(id);
    }

    // SEARCH - Αναζήτηση πελατών με βάση το όνομα ή τμήμα του ονόματος
    @Override
    public List<CustomerDTO> searchCustomers(String name) {
        // Αναζητά πελάτες με βάση το όνομα ή τμήμα του ονόματος
        List<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);

        if (customers.isEmpty()) {
            throw new RuntimeException("Δεν βρέθηκαν πελάτες με το όνομα ή το τμήμα του ονόματος: " + name);
        }

        // Μετατροπή σε λίστα DTO
        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
