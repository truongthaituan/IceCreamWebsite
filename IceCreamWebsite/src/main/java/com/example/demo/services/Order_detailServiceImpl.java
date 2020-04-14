package com.example.demo.services;

import com.example.demo.dto.OrderDTO;
import com.example.demo.models.Order_details;
import com.example.demo.repositories.Order_detailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class Order_detailServiceImpl implements Order_detailService {
    @Autowired
    Order_detailRepository order_detailRepository;
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Order_details> findAll() {
        return (List<Order_details>) order_detailRepository.findAll();
    }

    @Override
    public Optional<Order_details> getOrder_DetailById(Long order_details_id) {
        return order_detailRepository.findById(order_details_id);
    }

    @Override
    public void saveOrUpdate(Order_details order_details) {
        order_detailRepository.save(order_details);
    }

    @Override
    public void deleteOrder_Detail(Long order_details_id) {
        order_detailRepository.deleteById(order_details_id);
    }

    @Override
    @Transactional
    public List<Order_details> findOrderDetailsByOrder(Long orderId) {
        try {
            List<Order_details> order_details = em.createQuery("SELECT e FROM Order_details e where e.order.id = '"+orderId+"'").getResultList();
            return order_details;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Order_details> findOrderDetailsByRecipe(Long recipeId) {
        try {
            List<Order_details> order_details = em.createQuery("SELECT e FROM Order_details e where e.recipe.id = '"+recipeId+"'").getResultList();
            return order_details;
        } catch (Exception ex) {
            return null;
        }
    }

//
//    @Override
//    @Transactional
//    public List<Order_details> deleteOrderDetailsByOrder(Long orderId) {
//        try {
//            List<Order_details> order_details = em.createQuery("DELETE FROM Order_details where Order_details.order.id = '"+orderId+"'").getResultList();
//            return order_details;
//        } catch (Exception ex) {
//            return null;
//        }    }
}
