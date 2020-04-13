package com.example.demo.services;

import com.example.demo.models.Payment;
import com.example.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public List<Payment> findAll() {
        return (List<Payment>) paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(Long payment_id) {
        return paymentRepository.findById(payment_id);
    }

    @Override
    public void saveOrUpdate(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long payment_id) {
        paymentRepository.deleteById(payment_id);
    }

    @Override
    public Payment getPayById(Long payment_id) {
        return paymentRepository.findById(payment_id).get();
    }
}
