package com.example.demo.services;

import com.example.demo.models.order_details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.order_detailRepository;
import java.util.List;
import java.util.Optional;

@Service
public class order_detailServiceImpl implements order_detailService{
    @Autowired
    order_detailRepository order_detailRepository;
    @Override
    public List<order_details> findAll() {
        return (List<order_details>) order_detailRepository.findAll();
    }

    @Override
    public Optional<order_details> getOrder_DetailById(Long order_details_id) {
        return order_detailRepository.findById(order_details_id);
    }

    @Override
    public void saveOrUpdate(order_details order_details) {
        order_detailRepository.save(order_details);
    }

    @Override
    public void deleteOrder_Detail(Long order_details_id) {
        order_detailRepository.deleteById(order_details_id);
    }
}
