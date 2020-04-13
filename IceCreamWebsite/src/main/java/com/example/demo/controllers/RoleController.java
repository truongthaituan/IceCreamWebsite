package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> findRole() {
        List<Role> roles = roleService.findAll();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getAdminByUsername(@PathVariable("id") Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (!((Optional) role).isPresent()) {
            return new ResponseEntity<>(role.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/roles",
            method = RequestMethod.POST)
    public ResponseEntity<Role> createRole(@RequestBody Role role, UriComponentsBuilder builder) {
        role.setRole_name(role.getRole_name());
        roleService.saveOrUpdate(role);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/roles/{id}")
                .buildAndExpand(role.getId()).toUri());
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {

        Optional<Role> currentRole = roleService.getRoleById(id);
        if (!currentRole.isPresent()) {
            return new ResponseEntity<>(currentRole.get(), HttpStatus.NO_CONTENT);
        }

        currentRole.get().setRole_name(role.getRole_name());
        roleService.saveOrUpdate(currentRole.get());
        return new ResponseEntity<>(currentRole.get(), HttpStatus.OK);
     }
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (!role.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        roleService.deleteRole(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
