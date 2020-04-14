package com.example.demo.services;

import com.example.demo.dto.Response;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserUpdateDTO;
import com.example.demo.models.Role;
import com.example.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO getUserById(Long id);
    UserDTO registerUser(User user);
    UserDTO updateUser(User user);
    void deleteUser(Long id);
    UserDTO findByuserName(String username);
    UserDTO updateUserRoles(Long id, List<Long> roleIds);
    Boolean changePassword(UserUpdateDTO userUpdateDTO);
    Response login(String username, String password);
    boolean exists(User user);

}
