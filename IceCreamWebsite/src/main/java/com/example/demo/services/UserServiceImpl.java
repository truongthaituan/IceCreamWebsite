package com.example.demo.services;

//import com.example.demo.configSecurity.JwtUtil;
import com.example.demo.common.MapperUtil;
import com.example.demo.configSecurity.CustomUserDetails;
import com.example.demo.configSecurity.JwtUtil;
import com.example.demo.dto.Response;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserUpdateDTO;
import com.example.demo.exceptions.CustomException;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    //    @Autowired
//    private JwtUtil jwtUtil;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<UserDTO> findAll() {
       List<User> user = userRepository.findAll();
       return MapperUtil.mapList(user,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return MapperUtil.mapObject(userRepository.findById(id).get(),UserDTO.class);
    }

    @Override
    public UserDTO registerUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (Role r: user.getRoles()) {
            roles.add(roleRepository.findById(r.getId()).get());
        }
        // Update roles
        user.setRoles(roles);
        user.setUserName(user.getUserName());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setStatus(user.getStatus());
        user.setAvatar(user.getAvatar());
        return MapperUtil.mapObject(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(User user) {
        User currentUser = userRepository.findById(user.getUserId()).get();
        List<Role> roles = new ArrayList<>();
        for (Role r: user.getRoles()) {
            roles.add(roleRepository.findById(r.getId()).get());
        }
        // Update roles
        currentUser.setRoles(roles);
        currentUser.setUserName(user.getUserName());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setStatus(user.getStatus());
        currentUser.setAvatar(user.getAvatar());
        try {
            currentUser = userRepository.save(currentUser);
        } catch (Exception ex) {
            System.out.println("Exception occured: " + ex);
        }
        return MapperUtil.mapObject(currentUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByuserName(String userName) {
        return MapperUtil.mapObject(userRepository.findByuserName(userName),UserDTO.class);
    }

    @Override
    public UserDTO updateUserRoles(Long id, List<Long> roleIds) {
        User user = userRepository.findById(id).get();
        if (user == null) {
            System.out.println("User with Id: " + id + "not found");
        }
        List<Role> roles = new ArrayList<>();
        for(Long i : roleIds){
            Optional<Role> role = roleRepository.findById(i);
            if (role.isPresent()) {
                System.out.println("Not Found");
            }
            roles.add(role.get());
            }
            user.getRoles().addAll(roles);
            userRepository.save(user);
        return MapperUtil.mapObject(user, UserDTO.class);
    }

    @Override
    public Boolean changePassword(UserUpdateDTO userUpdateDTO) {
        Boolean statusChangePass = false;
        User currentUser = userRepository.findByuserName(userUpdateDTO.getUser().getUserName());
        if (currentUser == null) {
            System.out.println("User " + userUpdateDTO.getUser().getUserName() + " not found");
        }
        String currentPass = userUpdateDTO.getCurrentPassword();
        if (!currentPass.equals("")) {
            if (passwordEncoder.matches(currentPass, currentUser.getPassword())) {
                currentUser.setPassword(passwordEncoder.encode(userUpdateDTO.getNewPassword()));
                statusChangePass = true;
            } else {
                statusChangePass = false;
                System.out.println("Invalid password");
            }
        }
        List<Role> roles = new ArrayList<>();
        for(Role r : userUpdateDTO.getUser().getRoles()) {
            Optional<Role> role = roleRepository.findById(r.getId());
            if (role.isPresent()) {
                System.out.println("Not Found");
            }
            roles.add(role.get());
        }
        // Update roles
        currentUser.setRoles(roles);
        currentUser.setUserName(userUpdateDTO.getUser().getUserName());
        currentUser.setPhoneNumber(userUpdateDTO.getUser().getPhoneNumber());
        currentUser.setStatus(userUpdateDTO.getUser().getStatus());
        currentUser.setAvatar(userUpdateDTO.getUser().getAvatar());
        try {
            currentUser = userRepository.save(currentUser);
        } catch (Exception ex) {
            System.out.println("Exception occured: " + ex);
        }
        return statusChangePass;
    }
    @Override
    public Response login(String username, String password) {
        Response response;
        try {
            Authentication authentication =
                    authenticationManager
                            .authenticate(
                                    new UsernamePasswordAuthenticationToken(
                                            username,
                                            password));

            // Inject current user into security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String role =  String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            role.trim();
            role = role.substring(1);
            role = role.substring(0, role.length() - 1);
            UserDTO userDTO = MapperUtil.mapObject(userRepository.findByuserName(username),UserDTO.class);
            response = new Response(jwtUtil.generateTokenForUser((CustomUserDetails) authentication.getPrincipal()),
                    true,username,userDTO.getAvatar(),role);
        } catch (AuthenticationException ex) {
//            response = new Response("",false,"",
//                    "","");
            throw new CustomException("Loi",HttpStatus.OK);
        }
        return response;
    }
    @Override
    public boolean exists(User user) {
        return userRepository.findByuserName(user.getUserName()) != null;
    }
}
