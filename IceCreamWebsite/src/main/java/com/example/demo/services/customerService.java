package com.example.demo.services;

import com.example.demo.models.customer;

import java.util.List;
import java.util.Optional;

public interface customerService {
    List<customer> findAll();
    Optional<customer> getCustomerById(Long customer_id);
    void saveOrUpdate(customer customer);
    void deleteCustomer(Long customer_id);
    customer getCusById(Long id);
}
