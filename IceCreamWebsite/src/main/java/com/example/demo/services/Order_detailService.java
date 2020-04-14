package com.example.demo.services;

import com.example.demo.models.Order_details;

import java.util.List;
import java.util.Optional;

public interface Order_detailService {
    List<Order_details> findAll();
    Optional<Order_details> getOrder_DetailById(Long order_details_id);
    void saveOrUpdate(Order_details order_details);
    void deleteOrder_Detail(Long order_details_id);
    List<Order_details> findOrderDetailsByOrder(Long orderId);
    List<Order_details> findOrderDetailsByRecipe(Long recipeId);
//    List<Order_details> deleteOrderDetailsByOrder(Long orderId);
}
