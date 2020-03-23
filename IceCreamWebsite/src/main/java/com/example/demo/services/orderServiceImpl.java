package com.example.demo.services;

import com.example.demo.models.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.orderRepository;
import java.util.List;
import java.util.Optional;

@Service
public class orderServiceImpl implements orderService{
    @Autowired
    orderRepository orderRepository;
    @Override
    public List<order> findAll() {
        return (List<order>) orderRepository.findAll();
    }

    @Override
    public Optional<order> getOrderById(Long order_id) {
        return orderRepository.findById(order_id);
    }

    @Override
    public void saveOrUpdate(order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long order_id) {
        orderRepository.deleteById(order_id);
    }
}
