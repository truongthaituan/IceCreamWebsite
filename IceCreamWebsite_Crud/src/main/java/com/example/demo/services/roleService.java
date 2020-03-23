package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.models.role;
public interface roleService {
    List<role> findAll();
    Optional<role> getRoleById(Long id);
    void saveOrUpdate(role role);
    void deleteRole(Long id);
}
