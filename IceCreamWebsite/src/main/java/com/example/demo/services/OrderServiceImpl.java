package com.example.demo.services;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.OrderDTO;
import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<OrderDTO> findAll() {
        return MapperUtil.mapList(orderRepository.findAll(),OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderById(Long order_id) {
        return MapperUtil.mapObject(orderRepository.findById(order_id).get(),OrderDTO.class);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public OrderDTO saveOrUpdate(Order order) {
        return MapperUtil.mapObject(orderRepository.save(order),OrderDTO.class);
    }

    @Override
    public void deleteOrder(Long order_id) {
        orderRepository.deleteById(order_id);
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrderByCustomer(Long customerId) {
        try {
            List<OrderDTO> orderDTOS = em.createQuery("SELECT e FROM Order e where e.customer.customerId = '"+customerId+"'").getResultList();
            return orderDTOS;
        } catch (Exception ex) {
            return null;
        }
    }
}
