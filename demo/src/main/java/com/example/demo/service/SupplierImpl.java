package com.example.demo.service;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.mapper.SupplierMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    // Κατασκευαστής με Dependency Injection
    public SupplierImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    // CREATE
    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        // Μετατροπή DTO σε Entity
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        // Αποθήκευση στον πίνακα
        Supplier savedSupplier = supplierRepository.save(supplier);
        // Επιστροφή του αποθηκευμένου Supplier ως DTO
        return supplierMapper.toDTO(savedSupplier);
    }

    // READ ALL
    @Override
    public List<SupplierDTO> getAllSuppliers() {
        // Ανάκτηση όλων των προμηθευτών από τη βάση
        List<Supplier> suppliers = supplierRepository.findAll();
        // Μετατροπή της λίστας των Suppliers σε DTOs
        return suppliers.stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Override
    public SupplierDTO getSupplierById(int id) {
        // Ανάκτηση του προμηθευτή με βάση το ID
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));
        // Επιστροφή του προμηθευτή ως DTO
        return supplierMapper.toDTO(supplier);
    }


    // UPDATE
    // UPDATE
    public SupplierDTO updateSupplier(int id, SupplierDTO supplierDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));

        supplierMapper.updateEntityFromDTO(supplierDTO, existingSupplier);  // Χρησιμοποιεί τον Mapper για ενημέρωση

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return supplierMapper.toDTO(updatedSupplier);
    }



    // DELETE
    @Override
    public void deleteSupplier(int id) {
        // Έλεγχος αν ο προμηθευτής υπάρχει
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found with ID: " + id);
        }
        // Διαγραφή του προμηθευτή από τη βάση
        supplierRepository.deleteById(id);
    }
}
