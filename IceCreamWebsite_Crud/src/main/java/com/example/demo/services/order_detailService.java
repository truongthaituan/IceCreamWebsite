package com.example.demo.services;

import com.example.demo.models.order_details;

import java.util.List;
import java.util.Optional;

public interface order_detailService {
    List<order_details> findAll();
    Optional<order_details> getOrder_DetailById(Long order_details_id);
    void saveOrUpdate(order_details order_details);
    void deleteOrder_Detail(Long order_details_id);
}
