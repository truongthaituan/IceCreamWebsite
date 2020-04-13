package com.example.demo.controllers;

import com.example.demo.common.MapperUtil;
import com.example.demo.configSecurity.JwtUtil;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.LoginResDTO;
import com.example.demo.dto.Response;
import com.example.demo.services.CustomerService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticateController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> login(@RequestBody AuthRequest authRequest) {
        // Check user/admin
        Response response;
        try {
            return new ResponseEntity<>(
                    userService.login(authRequest.getUserName(), authRequest.getPassword()), HttpStatus.OK);
        } catch (Exception ex) {
            response = customerService.login(authRequest.getUserName(), authRequest.getPassword());
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
