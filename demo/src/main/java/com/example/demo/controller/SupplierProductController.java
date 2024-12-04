package com.example.demo.controller;

import com.example.demo.dto.SupplierProductDTO;
import com.example.demo.service.SupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier-products")
public class SupplierProductController {

    private final SupplierProductService supplierProductService;

    @Autowired
    public SupplierProductController(SupplierProductService supplierProductService) {
        this.supplierProductService = supplierProductService;
    }

    // Δημιουργία σχέσης προμηθευτή-προϊόντος
    @PostMapping
    public ResponseEntity<SupplierProductDTO> createSupplierProduct(@RequestBody SupplierProductDTO supplierProductDTO) {
        SupplierProductDTO createdSupplierProduct = supplierProductService.createSupplierProduct(supplierProductDTO);
        return new ResponseEntity<>(createdSupplierProduct, HttpStatus.CREATED);
    }

    // Ανάκτηση όλων των σχέσεων προμηθευτών-προϊόντων
    @GetMapping
    public ResponseEntity<List<SupplierProductDTO>> getAllSupplierProducts() {
        List<SupplierProductDTO> supplierProducts = supplierProductService.getAllSupplierProducts();
        return new ResponseEntity<>(supplierProducts, HttpStatus.OK);
    }

    // Ανάκτηση σχέσης προμηθευτή-προϊόντος με βάση το ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierProductDTO> getSupplierProductById(@PathVariable("id") int id) {
        SupplierProductDTO supplierProduct = supplierProductService.getSupplierProductById(id);
        return new ResponseEntity<>(supplierProduct, HttpStatus.OK);
    }

    // Διαγραφή σχέσης προμηθευτή-προϊόντος με βάση το ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierProduct(@PathVariable("id") int id) {
        supplierProductService.deleteSupplierProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
