package com.example.demo.services;

import com.example.demo.models.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.customerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class customerServiceImpl implements customerService {
    @Autowired
    customerRepository customerRepository;
    @Override
    public List<customer> findAll() {
        return (List<customer>) customerRepository.findAll();
    }

    @Override
    public Optional<customer> getCustomerById(Long customer_id) {
        return customerRepository.findById(customer_id);
    }

    @Override
    public void saveOrUpdate(customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long customer_id) {
        customerRepository.deleteById(customer_id);
    }

    @Override
    public customer getCusById(Long id) {
        return customerRepository.findById(id).get();
    }
}
