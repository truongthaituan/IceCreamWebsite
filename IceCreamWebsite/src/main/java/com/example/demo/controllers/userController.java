package com.example.demo.controllers;

import com.example.demo.configSecurity.JwtUtil;
import com.example.demo.models.AuthRequest;
import com.example.demo.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.services.userService;
import java.util.List;
import java.util.Optional;
import com.example.demo.configSecurity.JwtFilter;
@RestController
public class userController {
    @Autowired
     userService userService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
//    @RequestMapping(value = "users", method = RequestMethod.GET)
//    public ResponseEntity<List<user>> findRole() {
//        List<user> users = userService.findAll();
//        if (users.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<user> getAdminByUsername(@PathVariable("id") Long id) {
//        Optional<user> user = userService.getUserById(id);
//        if (!((Optional) user).isPresent()) {
//            return new ResponseEntity<>(user.get(), HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(user.get(), HttpStatus.OK);
//    }
//    @RequestMapping(value = "/users",
//            method = RequestMethod.POST)
//    public ResponseEntity<user> createRole(@RequestBody user user, UriComponentsBuilder builder) {
//        user.setName(user.getName());
//        user.setStatus(user.getStatus());
//        user.setPhone_number(user.getPhone_number());
//        user.setPassword(user.getPassword());
//        user.setAvatar(user.getAvatar());
//        userService.saveOrUpdate(user);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/users/{id}")
//                .buildAndExpand(user.getUser_id()).toUri());
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<user> updateRole(@PathVariable("id") Long id, @RequestBody user user) {
//
//        Optional<user> currentUser = userService.getUserById(id);
//        if (!currentUser.isPresent()) {
//            return new ResponseEntity<>(currentUser.get(), HttpStatus.NO_CONTENT);
//        }
//
//        currentUser.get().setName(user.getName());
//        currentUser.get().setStatus(user.getStatus());
//        currentUser.get().setPhone_number(user.getPhone_number());
//        currentUser.get().setPassword(user.getPassword());
//        currentUser.get().setAvatar(user.getAvatar());
//        userService.saveOrUpdate(currentUser.get());
//        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
//    }
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
//        Optional<user> user = userService.getUserById(id);
//        if (!user.isPresent()) {
//            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
//        }
//        userService.deleteUser(id);
//        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
//    }
}
