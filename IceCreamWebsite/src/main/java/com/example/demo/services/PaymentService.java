package com.example.demo.services;

import com.example.demo.models.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> findAll();
    Optional<Payment> getPaymentById(Long payment_id);
    void saveOrUpdate(Payment payment);
    void deletePayment(Long payment_id);
    Payment getPayById(Long payment_id);
}
