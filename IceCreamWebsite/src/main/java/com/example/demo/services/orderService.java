package com.example.demo.services;

import com.example.demo.dto.OrderDTO;
import com.example.demo.models.Order;
import com.example.demo.models.Recipe;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> findAll();

    OrderDTO getOrderById(Long order_id);

    Order findOrderById(Long id);

    OrderDTO saveOrUpdate(Order order);

    void deleteOrder(Long order_id);

    List<OrderDTO> getOrderByCustomer(Long customerId);
}
