package com.example.demo.controllers;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.CustomerChangePassDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.models.ConfirmationToken;
import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.dto.StatusCRUD;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customers")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> findCustomer() {
        List<CustomerDTO> customers = customerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/username/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomerByUserNme(@PathVariable("userName") String userName) {
        Customer customer = customerService.findUserByName(userName);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(MapperUtil.mapObject(customer, CustomerDTO.class), HttpStatus.OK);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST)
    public ResponseEntity<ConfirmationToken> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.registerCustomer(customer), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
       Customer currentCustomer = customerService.findCustomerById(id);
        if (currentCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentCustomer.setUserName(customer.getUserName());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setPhone(customer.getPhone());
        currentCustomer.setPassword(customer.getPassword());
        currentCustomer.setDateOfBirth(customer.getDateOfBirth());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setGender(customer.getGender());
        currentCustomer.setAvatar(customer.getAvatar());
        currentCustomer.setStatus(customer.getStatus());
        currentCustomer.setNumberOfLoginFailed(customer.getNumberOfLoginFailed());
        customerService.updateCustomer(currentCustomer);
        return new ResponseEntity<>(MapperUtil.mapObject(currentCustomer,CustomerDTO.class), HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusCRUD> deleteFaq(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        StatusCRUD statusCRUD = new StatusCRUD("Delete Customer Successfully");
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(statusCRUD, HttpStatus.OK);
    }

    @RequestMapping(value = "/changePasswordCustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusCRUD> changePassword(@RequestBody CustomerChangePassDTO customerChangePassDTO) {
        StatusCRUD statusCRUD;
        Boolean statusChange = customerService.changePassword(customerChangePassDTO);
        if(statusChange){
           statusCRUD = new StatusCRUD("Change Password Successfully!");
        } else {
            statusCRUD = new StatusCRUD("Invalid Password!");
        }
        return new ResponseEntity<>(statusCRUD, HttpStatus.OK);
    }
}
