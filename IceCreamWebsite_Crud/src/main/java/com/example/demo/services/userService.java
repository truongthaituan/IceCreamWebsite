package com.example.demo.services;

import com.example.demo.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repositories.userRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface userService {
    List<user> findAll();
    Optional<user> getUserById(Long id);
    void saveOrUpdate(user user);
    void deleteUser(Long id);
//    @Autowired
//    userRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        user user = userRepository.findByUserName(username);
//        return new org.springframework.security.core.userdetails.User(user.getUserName(),
//                user.getPassword(), new ArrayList<>());
//    }
}
