package com.example.demo.service;

import com.example.demo.dto.SupplierProductDTO;
import com.example.demo.model.SupplierProduct;
import com.example.demo.repository.SupplierProductRepository;
import com.example.demo.mapper.SupplierProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierProductImpl implements SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;
    private final SupplierProductMapper supplierProductMapper;

    @Autowired
    public SupplierProductImpl(SupplierProductRepository supplierProductRepository, SupplierProductMapper supplierProductMapper) {
        this.supplierProductRepository = supplierProductRepository;
        this.supplierProductMapper = supplierProductMapper;
    }

    // CREATE
    @Override
    public SupplierProductDTO createSupplierProduct(SupplierProductDTO supplierProductDTO) {
        SupplierProduct supplierProduct = supplierProductMapper.toEntity(supplierProductDTO);
        SupplierProduct savedProduct = supplierProductRepository.save(supplierProduct);
        return supplierProductMapper.toDTO(savedProduct);
    }

    // READ ALL
    @Override
    public List<SupplierProductDTO> getAllSupplierProducts() {
        List<SupplierProduct> supplierProducts = supplierProductRepository.findAll();
        return supplierProducts.stream()
                .map(supplierProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Override
    public SupplierProductDTO getSupplierProductById(int id) {
        SupplierProduct supplierProduct = supplierProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SupplierProduct not found with ID: " + id));
        return supplierProductMapper.toDTO(supplierProduct);
    }

    // UPDATE
    @Override
    public SupplierProductDTO updateSupplierProduct(int id, SupplierProductDTO supplierProductDTO) {
        SupplierProduct existingSupplierProduct = supplierProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SupplierProduct not found with ID: " + id));

        // Χρησιμοποιούμε το ModelMapper για να μεταφέρουμε τα δεδομένα του DTO στην οντότητα
        supplierProductMapper.toEntity(supplierProductDTO);

        // Αποθήκευση του ενημερωμένου SupplierProduct
        SupplierProduct updatedSupplierProduct = supplierProductRepository.save(existingSupplierProduct);

        // Επιστροφή του ενημερωμένου SupplierProduct ως DTO
        return supplierProductMapper.toDTO(updatedSupplierProduct);
    }

    // DELETE
    @Override
    public void deleteSupplierProduct(int id) {
        if (!supplierProductRepository.existsById(id)) {
            throw new RuntimeException("SupplierProduct not found with ID: " + id);
        }
        supplierProductRepository.deleteById(id);
    }
}
