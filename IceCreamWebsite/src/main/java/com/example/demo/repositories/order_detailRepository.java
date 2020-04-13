package com.example.demo.repositories;

import com.example.demo.models.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_detailRepository extends JpaRepository<Order_details, Long> {
}
