package com.example.demo.controllers;

//import com.example.demo.configSecurity.JwtUtil;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.StatusCRUD;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserUpdateDTO;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//import com.example.demo.configSecurity.JwtFilter;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/username/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findUserByUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userService.findByuserName(userName), HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findUsers() {
        List<UserDTO> userDTOS = userService.findAll();
        if (userDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusCRUD> changePassword(@RequestBody UserUpdateDTO userUpdateDTO) {
        StatusCRUD statusCRUD;
        Boolean statusChange = userService.changePassword(userUpdateDTO);
        if(statusChange){
            statusCRUD = new StatusCRUD("Change Password Successfully!");
        } else {
            statusCRUD = new StatusCRUD("Invalid Password!");
        }
        return new ResponseEntity<>(statusCRUD, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/users/{id}")
//                .buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<>(MapperUtil.mapObject(userService.registerUser(user),UserDTO.class), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        UserDTO userDTO = userService.updateUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusCRUD> deleteUser(@PathVariable("id") Long id) {
        UserDTO user = userService.getUserById(id);
        userService.deleteUser(id);
        StatusCRUD statusCRUD = new StatusCRUD("delete user successfully!");
        return new ResponseEntity<>(statusCRUD, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUserRoles(@PathVariable("id") Long id, @RequestBody Map<String, List<Long>> roleIds) {
        userService.updateUserRoles(id, roleIds.get("roleIds"));
        return new ResponseEntity<>("Update UserRole Successfully!",HttpStatus.NO_CONTENT);
    }
}
