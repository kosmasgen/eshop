package com.example.demo.controller;

import com.example.demo.dto.ProductCategoryDTO;
import com.example.demo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    // Εισάγουμε το ProductCategoryService μέσω Dependency Injection
    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    // Δημιουργία νέας κατηγορίας προϊόντος
    @PostMapping
    public ResponseEntity<ProductCategoryDTO> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO createdCategory = productCategoryService.createProductCategory(productCategoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Ανάκτηση όλων των κατηγοριών προϊόντων
    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories() {
        List<ProductCategoryDTO> categories = productCategoryService.getAllProductCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Ανάκτηση κατηγορίας προϊόντος με βάση το ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductCategoryById(@PathVariable("id") int id) {
        ProductCategoryDTO category = productCategoryService.getProductCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Ενημέρωση κατηγορίας προϊόντος με βάση το ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> updateProductCategory(
            @PathVariable("id") int id,
            @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO updatedCategory = productCategoryService.updateProductCategory(id, productCategoryDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // Διαγραφή κατηγορίας προϊόντος με βάση το ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable("id") int id) {
        productCategoryService.deleteProductCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
