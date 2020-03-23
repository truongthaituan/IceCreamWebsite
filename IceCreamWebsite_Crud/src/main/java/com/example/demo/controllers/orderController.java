package com.example.demo.controllers;

import com.example.demo.models.order;
import com.example.demo.services.orderService;
import com.example.demo.services.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.models.customer;
import com.example.demo.models.payment;
import com.example.demo.services.paymentService;
import java.util.List;
import java.util.Optional;

@Controller
public class orderController {
    @Autowired
    orderService orderService;
    @Autowired
    customerService customerService;
    @Autowired
    paymentService paymentService;
    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public ResponseEntity<List<order>> findOrder() {
        List<order> orders = orderService.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<order> getOrderById(@PathVariable("id") Long id) {
        Optional<order> order = orderService.getOrderById(id);
        if (!((Optional) order).isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/orders",
            method = RequestMethod.POST)
    public ResponseEntity<order> createOrder(@RequestBody order order, UriComponentsBuilder builder) {
//        System.out.print(order.getCustomer().getCustomer_id());
//        customer customer = customerService.getCusById(order.getCustomer().getCustomer_id());
//        order.setCustomer(customer);
//        System.out.print(customer);
//        order.setPayment_option(order.getPayment_option());
//        payment payment = paymentService.getPayById(order.getPayment().getPayment_id());
//        System.out.print(payment);
//        order.setPayment(payment);
//        order.setCreate_date(order.getCreate_date());
//        order.setDelivery_detail(order.getDelivery_detail());
//        order.setNotes(order.getNotes());
//        order.setStatus(order.getStatus());
        orderService.saveOrUpdate(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/orders/{id}")
                .buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity<order> updateOrder(@PathVariable("id") Long id, @RequestBody order order) {
        Optional<order> currentOrder = orderService.getOrderById(id);
        if (!currentOrder.isPresent()) {
            return new ResponseEntity<>(currentOrder.get(), HttpStatus.NO_CONTENT);
        }
        System.out.print(order.getCustomer().getCustomer_id());
//        customer customer = customerService.getCusById(order.getCustomer().getCustomer_id());
        currentOrder.get().setCustomer(order.getCustomer());
        currentOrder.get().setPayment_option(order.getPayment_option());
//        payment payment = paymentService.getPayById(order.getPayment().getPayment_id());
        currentOrder.get().setPayment(order.getPayment());
        currentOrder.get().setCreate_date(order.getCreate_date());
        currentOrder.get().setDelivery_detail(order.getDelivery_detail());
        currentOrder.get().setNotes(order.getNotes());
        currentOrder.get().setStatus(order.getStatus());
        orderService.saveOrUpdate(currentOrder.get());
        return new ResponseEntity<>(currentOrder.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        Optional<order> order = orderService.getOrderById(id);
        if (!order.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
