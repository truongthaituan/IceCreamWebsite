package com.example.demo.configSecurity;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByuserName(username);
        System.out.println("customer" + customer);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        if (!customer.getStatus()) {
            System.out.println("Customer has been disabled");
        }
        return new CustomerDetails(customer);
    }
}
