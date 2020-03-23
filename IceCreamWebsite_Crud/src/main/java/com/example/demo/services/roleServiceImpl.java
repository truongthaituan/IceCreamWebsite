package com.example.demo.services;

import com.example.demo.models.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.repositories.roleRepository;
@Service
public class roleServiceImpl implements roleService{
    @Autowired
    roleRepository roleRepository;
    @Override
    public List<role> findAll() {
        return (List<role>) roleRepository.findAll();
    }

    @Override
    public Optional<role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
