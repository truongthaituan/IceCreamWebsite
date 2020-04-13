package com.example.demo.services;

import com.example.demo.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> getRoleById(Long id);
    void saveOrUpdate(Role role);
    void deleteRole(Long id);
}
