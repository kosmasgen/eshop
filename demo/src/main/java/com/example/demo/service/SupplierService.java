package com.example.demo.service;

import com.example.demo.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    SupplierDTO createSupplier(SupplierDTO supplierDTO);
    List<SupplierDTO> getAllSuppliers();
    SupplierDTO getSupplierById(int id);
    SupplierDTO updateSupplier(int id, SupplierDTO supplierDTO);
    void deleteSupplier(int id);
}
