package com.example.demo.mapper;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderMapper() {
        this.modelMapper = new ModelMapper();
    }

    public OrderDTO toDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public Order toEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    public void toEntity(OrderDTO orderDTO, Order existingOrder) {
        modelMapper.map(orderDTO, existingOrder);
    }
}
