package com.example.demo.service;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.mapper.SupplierMapper;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class SupplierImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final OrderRepository orderRepository;


    public SupplierImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper,OrderRepository  orderRepository ) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDTO(savedSupplier);
    }

    @Override
    public SupplierDTO updateSupplier(int id, SupplierDTO supplierDTO) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
        if (existingSupplier.isPresent()) {
            Supplier supplier = existingSupplier.get();
            supplierMapper.updateEntityFromDTO(supplierDTO, supplier);
            Supplier updatedSupplier = supplierRepository.save(supplier);
            return supplierMapper.toDTO(updatedSupplier);
        } else {
            throw new RuntimeException("Ο προμηθευτής με ID " + id + " δεν βρέθηκε.");
        }
    }

    @Override
    public void deleteSupplier(int id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ο προμηθευτής με ID " + id + " δεν βρέθηκε.");
        }
    }

    @Override
    public SupplierDTO getSupplierById(int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.map(supplierMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ο προμηθευτής με ID " + id + " δεν βρέθηκε."));
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDTO> findSuppliersByName(String name) {
        List<Supplier> suppliers = supplierRepository.findByFirstNameContainingIgnoreCase(name);
        return suppliers.stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDTO> findSuppliersByLocation(String location) {
        List<Supplier> suppliers = supplierRepository.findByLocationContainingIgnoreCase(location);
        return suppliers.stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Double calculateTurnover(Integer supplierId, LocalDate startDate, LocalDate endDate) {
        // Βρες όλες τις παραγγελίες για τον προμηθευτή
        List<Order> orders = orderRepository.findBySupplierId(supplierId);

        // Φιλτράρισε τις παραγγελίες με βάση τις ημερομηνίες (μέρα, μήνας, έτος)
        return orders.stream()
                .filter(order -> {
                    LocalDate orderDate = order.getCreatedAt().toLocalDate();
                    return (orderDate.isEqual(startDate) || orderDate.isAfter(startDate)) &&
                            (orderDate.isEqual(endDate) || orderDate.isBefore(endDate));
                })
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

}
