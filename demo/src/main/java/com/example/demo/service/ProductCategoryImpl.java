package com.example.demo.service;

import com.example.demo.dto.ProductCategoryDTO;
import com.example.demo.model.ProductCategory;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;





    @Autowired
    public ProductCategoryImpl(ProductCategoryRepository productCategoryRepository,
                                  ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    // CREATE
    public ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
        ProductCategory savedCategory = productCategoryRepository.save(productCategory);
        return productCategoryMapper.toDTO(savedCategory);
    }

    // READ ALL
    public List<ProductCategoryDTO> getAllProductCategories() {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        return categories.stream()
                .map(productCategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public ProductCategoryDTO getProductCategoryById(int id) {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        return productCategoryMapper.toDTO(productCategory);
    }

    // UPDATE
    public ProductCategoryDTO updateProductCategory(int id, ProductCategoryDTO productCategoryDTO) {
        ProductCategory existingCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        existingCategory.setName(productCategoryDTO.getName());
        ProductCategory updatedCategory = productCategoryRepository.save(existingCategory);
        return productCategoryMapper.toDTO(updatedCategory);
    }

    // DELETE
    public void deleteProductCategory(int id) {
        if (!productCategoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with ID: " + id);
        }
        productCategoryRepository.deleteById(id);
    }
}
