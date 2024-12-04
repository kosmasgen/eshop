package com.example.demo.mapper;

import com.example.demo.dto.SupplierProductDTO;
import com.example.demo.model.SupplierProduct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SupplierProductMapper {
    private final ModelMapper modelMapper;

    public SupplierProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    public SupplierProductDTO toDTO(SupplierProduct supplierProduct) {
        return modelMapper.map(supplierProduct, SupplierProductDTO.class);
    }

    public SupplierProduct toEntity(SupplierProductDTO supplierProductDTO) {
        return modelMapper.map(supplierProductDTO, SupplierProduct.class);
    }
}
