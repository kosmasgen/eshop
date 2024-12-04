package com.example.demo.mapper;

import com.example.demo.dto.ProductCategoryDTO;
import com.example.demo.model.ProductCategory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {
    private final ModelMapper modelMapper;

    public ProductCategoryMapper() {
        this.modelMapper = new ModelMapper();
    }

    public ProductCategoryDTO toDTO(ProductCategory productCategory) {
        return modelMapper.map(productCategory, ProductCategoryDTO.class);
    }

    public ProductCategory toEntity(ProductCategoryDTO productCategoryDTO) {
        return modelMapper.map(productCategoryDTO, ProductCategory.class);
    }

}
