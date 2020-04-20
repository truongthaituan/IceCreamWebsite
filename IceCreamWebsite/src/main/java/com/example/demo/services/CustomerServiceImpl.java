package com.example.demo.services;

import com.example.demo.common.MapperUtil;
import com.example.demo.configSecurity.CustomerDetails;
import com.example.demo.configSecurity.JwtUtil;
import com.example.demo.dto.CustomerChangePassDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.Response;
import com.example.demo.models.ConfirmationToken;
import com.example.demo.models.Customer;
import com.example.demo.repositories.ConfirmationTokenRepository;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public List<CustomerDTO> findAll() {
        return MapperUtil.mapList(customerRepository.findAll(), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerById(Long customer_id) {
        return MapperUtil.mapObject(customerRepository.findById(customer_id).get(), CustomerDTO.class);
    }

    @Override
    public Customer findCustomerById(Long customer_id) {
        return customerRepository.findById(customer_id).get();
    }

    @Override
    public ConfirmationToken registerCustomer(Customer customer) {
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        customerRepository.save(customer);
//        return MapperUtil.mapObject(,CustomerDTO.class);
        ConfirmationToken confirmationToken = new ConfirmationToken(customer);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(customer.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("truongthaituan98@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:4200/confirm-account/"+confirmationToken.getConfirmationToken());
        emailSenderService.sendEmail(mailMessage);
        return confirmationToken;
    }

    @Override
    public CustomerDTO updateCustomer(Customer customer) {
        return MapperUtil.mapObject(customerRepository.save(customer), CustomerDTO.class);
    }


//    @Override
//    public void saveOrUpdate(Customer customer) {
//        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
//        customerRepository.save(customer);
//    }

    @Override
    public void deleteCustomer(Long customer_id) {
        customerRepository.deleteById(customer_id);
    }

    @Override
    public Customer getCusById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer findUserByName(String userName) {
       return customerRepository.findByuserName(userName);
    }

    @Override
    public Boolean changePassword(CustomerChangePassDTO customerChangePassDTO) {
        Boolean flagChangePassword = false;
        Customer currentCustomer = customerRepository.findByuserName(customerChangePassDTO.getCustomer().getUserName());
        String currentPass = customerChangePassDTO.getCurrentPassword();
        if (!currentPass.equals("")) {
            if (passwordEncoder.matches(currentPass, currentCustomer.getPassword())) {
                currentCustomer.setPassword(passwordEncoder.encode(customerChangePassDTO.getNewPassword()));
                flagChangePassword = true;
            } else {
                flagChangePassword = false;
            }
        }
        // Update roles
        currentCustomer.setUserName(customerChangePassDTO.getCustomer().getUserName());
        currentCustomer.setEmail(customerChangePassDTO.getCustomer().getEmail());
        currentCustomer.setPhone(customerChangePassDTO.getCustomer().getPhone());
        currentCustomer.setDateOfBirth(customerChangePassDTO.getCustomer().getDateOfBirth());
        currentCustomer.setAddress(customerChangePassDTO.getCustomer().getAddress());
        currentCustomer.setGender(customerChangePassDTO.getCustomer().getGender());
        currentCustomer.setAvatar(customerChangePassDTO.getCustomer().getAvatar());
        currentCustomer.setStatus(customerChangePassDTO.getCustomer().getStatus());
        currentCustomer.setNumberOfLoginFailed(customerChangePassDTO.getCustomer().getNumberOfLoginFailed());
        try {
            customerRepository.save(currentCustomer);
        } catch (Exception ex) {
            System.out.println("Exception occured: " + ex);
        }
        return flagChangePassword;
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
            // Inject current customer into security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String role =  String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            role.trim();
            role = role.substring(1);
            role = role.substring(0, role.length() - 1);
            Customer customer = customerRepository.findByuserName(username);
            response = new Response(jwtUtil.generateTokenForCustomer((CustomerDetails) authentication.getPrincipal()),
                    true,username,customer.getAvatar(),role);
        } catch (AuthenticationException ex) {
            response = new Response("",false,"",
                    "","");
        }
        return response;
    }
}
