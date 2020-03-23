package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.payment;
@Repository
public interface paymentRepository extends JpaRepository<payment, Long> {
}
