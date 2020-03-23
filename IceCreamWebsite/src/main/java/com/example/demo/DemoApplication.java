package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.repositories.roleRepository;
import com.example.demo.repositories.userRepository;
import com.example.demo.models.user;
import com.example.demo.models.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Arrays;


@SpringBootApplication
public class DemoApplication {
//    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner mappingDemo(roleRepository roleRepository,
//                                         userRepository userRepository) {
//        return args -> {
//
//            // create a user
//            user user = new user();
//            user.setName("Ramesh");
//            user.setStatus(true);
//            user.setAvatar("image/tuan.jpg");
//            user.setPhone_number("02211111111");
//            user.setPassword("123456");
//            // save the student
//            userRepository.save(user);
//            // Create project1
//            role role = new role();
//            role.setRole_name("customerRepository");
//
//            // Create project2
//            role role1 = new role();
//            role1.setRole_name("admin");
//
//            // save courses
//            roleRepository.saveAll(Arrays.asList(role, role1));
//
//            // add courses to the student
//            user.getRoles().addAll(Arrays.asList(role, role1));
//            userRepository.save(user);
//
//        };
//    }
}
