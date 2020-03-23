package com.example.demo.services;

import com.example.demo.models.order;

import java.util.List;
import java.util.Optional;

public interface orderService {
    List<order> findAll();
    Optional<order> getOrderById(Long order_id);
    void saveOrUpdate(order order);
    void deleteOrder(Long order_id);
}
