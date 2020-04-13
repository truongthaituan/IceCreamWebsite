package com.example.demo.dto;
import com.example.demo.models.Role;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDTO {

    private Long userId;

    @NotNull(message = "Username is required")
    @Size(min = 6, max = 14, message = "Username must be between 6 and 14 digits.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only characters allowed in the username.")
    private String userName;

    private Boolean status;

    private String phoneNumber;

    private String avatar;
    public UserDTO() {
    }
    public UserDTO(Long userId, @NotNull(message = "Username is required")
    @Size(min = 6, max = 14, message = "Username must be between 6 and 14 digits.") @Pattern(regexp = "^[a-zA-Z]+$", message = "Only characters allowed in the username.")
            String userName, Boolean status, String phoneNumber, String avatar, List<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private List<Role> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
