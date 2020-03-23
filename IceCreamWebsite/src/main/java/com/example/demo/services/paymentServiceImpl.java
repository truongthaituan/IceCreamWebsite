package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.payment;
import com.example.demo.repositories.paymentRepository;
import java.util.List;
import java.util.Optional;

@Service
public class paymentServiceImpl implements paymentService{
    @Autowired
    paymentRepository paymentRepository;
    @Override
    public List<payment> findAll() {
        return (List<payment>) paymentRepository.findAll();
    }

    @Override
    public Optional<payment> getPaymentById(Long payment_id) {
        return paymentRepository.findById(payment_id);
    }

    @Override
    public void saveOrUpdate(payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long payment_id) {
        paymentRepository.deleteById(payment_id);
    }

    @Override
    public payment getPayById(Long payment_id) {
        return paymentRepository.findById(payment_id).get();
    }
}
