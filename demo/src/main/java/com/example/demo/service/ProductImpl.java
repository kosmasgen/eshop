package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // CREATE
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    // READ ALL
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Override
    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return productMapper.toDTO(product);
    }

    // UPDATE
    @Override
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        // Βρίσκουμε το υπάρχον προϊόν με το ID
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        // Χρησιμοποιούμε το ModelMapper για να μεταφέρουμε τα δεδομένα του DTO στην οντότητα
        productMapper.toEntity(productDTO);  // Αντιγράφουμε τα πεδία από το DTO στην οντότητα

        // Αποθηκεύουμε το ενημερωμένο προϊόν στη βάση δεδομένων
        Product updatedProduct = productRepository.save(existingProduct);

        // Επιστρέφουμε το ενημερωμένο προϊόν ως DTO
        return productMapper.toDTO(updatedProduct);
    }

    // DELETE
    @Override
    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }
}
