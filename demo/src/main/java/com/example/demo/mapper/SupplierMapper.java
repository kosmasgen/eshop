package com.example.demo.mapper;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.model.Supplier;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    private final ModelMapper modelMapper;

    public SupplierMapper() {
        this.modelMapper = new ModelMapper();
    }

    // Μετατροπή από Supplier σε SupplierDTO
    public SupplierDTO toDTO(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDTO.class);
    }

    // Μετατροπή από SupplierDTO σε Supplier
    public Supplier toEntity(SupplierDTO supplierDTO) {
        return modelMapper.map(supplierDTO, Supplier.class);
    }

    // Ενημέρωση του Supplier με τα δεδομένα από το SupplierDTO
    public void updateEntityFromDTO(SupplierDTO supplierDTO, Supplier supplier) {
        modelMapper.map(supplierDTO, supplier);  // Χρησιμοποιεί το ModelMapper για να ενημερώσει το supplier
    }
}
