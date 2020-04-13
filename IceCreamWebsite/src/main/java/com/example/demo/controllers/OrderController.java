package com.example.demo.controllers;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.StatusCRUD;
import com.example.demo.models.Order;
import com.example.demo.services.CustomerService;
import com.example.demo.services.OrderService;
import com.example.demo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PaymentService paymentService;
    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> findOrder() {
        List<OrderDTO> orders = orderService.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        OrderDTO order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getOrderByCustomer(@PathVariable("id") Long id) {
        List<OrderDTO> order = orderService.getOrderByCustomer(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders",
            method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, UriComponentsBuilder builder) {
        orderService.saveOrUpdate(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/orders/{id}")
                .buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
       Order currentOrder = orderService.findOrderById(id);
        if (currentOrder == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentOrder.setCustomer(order.getCustomer());
        currentOrder.setPaymentOption(order.getPaymentOption());
        currentOrder.setPayment(order.getPayment());
        currentOrder.setCreateDate(order.getCreateDate());
        currentOrder.setDeliveryDetail(order.getDeliveryDetail());
        currentOrder.setNotes(order.getNotes());
        currentOrder.setStatus(order.getStatus());
        return new ResponseEntity<>(orderService.saveOrUpdate(currentOrder), HttpStatus.OK);
    }
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusCRUD> deleteOrder(@PathVariable("id") Long id) {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(new StatusCRUD("Empty"), HttpStatus.NO_CONTENT);
        }
        orderService.deleteOrder(id);
        return new ResponseEntity<>(new StatusCRUD("Delete Order Successfully!"), HttpStatus.OK);
    }
}
