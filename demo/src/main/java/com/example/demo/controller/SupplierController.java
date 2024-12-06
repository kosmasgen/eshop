package com.example.demo.controller;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Δημιουργία Προμηθευτή
    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@Validated @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO createdSupplier = supplierService.createSupplier(supplierDTO);
        return ResponseEntity.ok(createdSupplier);
    }

    // Ενημέρωση Προμηθευτή
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable int id, @Validated @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updatedSupplier = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

    // Διαγραφή Προμηθευτή
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable int id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Ο προμηθευτής διαγράφηκε επιτυχώς.");
    }

    // Εύρεση Προμηθευτή με ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable int id) {
        SupplierDTO supplierDTO = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplierDTO);
    }

    // Εύρεση Όλων των Προμηθευτών
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // Αναζήτηση Προμηθευτών με Όνομα ή Τμήμα Ονόματος
    @GetMapping("/search/name")
    public ResponseEntity<List<SupplierDTO>> findSuppliersByName(@RequestParam String name) {
        List<SupplierDTO> suppliers = supplierService.findSuppliersByName(name);
        return ResponseEntity.ok(suppliers);
    }

    // Αναζήτηση Προμηθευτών με Τοποθεσία
    @GetMapping("/search/location")
    public ResponseEntity<List<SupplierDTO>> findSuppliersByLocation(@RequestParam String location) {
        List<SupplierDTO> suppliers = supplierService.findSuppliersByLocation(location);
        return ResponseEntity.ok(suppliers);
    }
    @GetMapping("/{supplierId}/turnover")
    public ResponseEntity<Double> calculateTurnover(
            @PathVariable Integer supplierId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Double turnover = supplierService.calculateTurnover(supplierId, startDate, endDate);
        return ResponseEntity.ok(turnover);
    }
}
