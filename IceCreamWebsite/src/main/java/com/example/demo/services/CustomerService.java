package com.example.demo.services;

import com.example.demo.dto.CustomerChangePassDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.Response;
import com.example.demo.models.ConfirmationToken;
import com.example.demo.models.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll();
    CustomerDTO getCustomerById(Long customer_id);
    Customer findCustomerById(Long customer_id);
//    void saveOrUpdate(Customer customer);
    ConfirmationToken registerCustomer(Customer customer);
    CustomerDTO updateCustomer(Customer customer);
    void deleteCustomer(Long customer_id);
    Customer getCusById(Long id);
    Customer findUserByName(String username);
    Boolean changePassword(CustomerChangePassDTO userUpdateDTO);
    Response login(String username, String password);
}
