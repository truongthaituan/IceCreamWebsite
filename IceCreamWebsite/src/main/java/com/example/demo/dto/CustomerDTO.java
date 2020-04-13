package com.example.demo.dto;

import java.util.Date;

public class CustomerDTO {
    private Long customerId;
    private String userName;
    private String email;
    private String phone;
    private String password;
    private Date dateOfBirth;
    private String address;
    private Boolean gender;
    private String avatar;
    private Boolean status;
    private Integer numberOfLoginFailed;

    public CustomerDTO(Long customerId, String userName, String email, String phone, String password, Date dateOfBirth,
                       String address, Boolean gender, String avatar, Boolean status, Integer numberOfLoginFailed) {
        this.customerId = customerId;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gender = gender;
        this.avatar = avatar;
        this.status = status;
        this.numberOfLoginFailed = numberOfLoginFailed;
    }

    public CustomerDTO() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getNumberOfLoginFailed() {
        return numberOfLoginFailed;
    }

    public void setNumberOfLoginFailed(Integer numberOfLoginFailed) {
        this.numberOfLoginFailed = numberOfLoginFailed;
    }
}
