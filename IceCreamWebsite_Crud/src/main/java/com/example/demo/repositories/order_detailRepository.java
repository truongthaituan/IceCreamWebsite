package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.order_details;
@Repository
public interface order_detailRepository extends JpaRepository<order_details, Long> {
}
