package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.demo.models.role;
import com.example.demo.services.roleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Controller
public class roleController {
    @Autowired
    roleService roleService;

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public ResponseEntity<List<role>> findRole() {
        List<role> roles = roleService.findAll();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<role> getAdminByUsername(@PathVariable("id") Long id) {
        Optional<role> role = roleService.getRoleById(id);
        if (!((Optional) role).isPresent()) {
            return new ResponseEntity<>(role.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/roles",
            method = RequestMethod.POST)
    public ResponseEntity<role> createRole(@RequestBody role role, UriComponentsBuilder builder) {
        role.setRole_name(role.getRole_name());
        roleService.saveOrUpdate(role);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/roles/{id}")
                .buildAndExpand(role.getRole_id()).toUri());
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
    public ResponseEntity<role> updateRole(@PathVariable("id") Long id, @RequestBody role role) {

        Optional<role> currentRole = roleService.getRoleById(id);
        if (!currentRole.isPresent()) {
            return new ResponseEntity<>(currentRole.get(), HttpStatus.NO_CONTENT);
        }

        currentRole.get().setRole_name(role.getRole_name());
        roleService.saveOrUpdate(currentRole.get());
        return new ResponseEntity<>(currentRole.get(), HttpStatus.OK);
     }
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        Optional<role> role = roleService.getRoleById(id);
        if (!role.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        roleService.deleteRole(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
