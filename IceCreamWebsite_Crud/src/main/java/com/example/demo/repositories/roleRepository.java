package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.role;
@Repository
public interface roleRepository extends JpaRepository<role, Long> {

}
