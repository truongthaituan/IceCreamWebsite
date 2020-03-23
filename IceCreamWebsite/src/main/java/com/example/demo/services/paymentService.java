package com.example.demo.services;

import com.example.demo.models.payment;

import java.util.List;
import java.util.Optional;

public interface paymentService {
    List<payment> findAll();
    Optional<payment> getPaymentById(Long payment_id);
    void saveOrUpdate(payment payment);
    void deletePayment(Long payment_id);
    payment getPayById(Long payment_id);
}
