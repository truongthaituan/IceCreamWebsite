package com.example.demo.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "date_of_birth")
    private Date date_of_birth;
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean gender;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "status")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;
    @Column(name = "number_of_login_failed")
    private Integer number_of_login_failed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<order> orders = new HashSet<>();
    public customer(String name, String email, String phone, String password, Date date_of_birth, String address,
                    Boolean gender, String avatar, Boolean status, Integer number_of_login_failed) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.gender = gender;
        this.avatar = avatar;
        this.status = status;
        this.number_of_login_failed = number_of_login_failed;
    }

    public customer() {
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public Integer getNumber_of_login_failed() {
        return number_of_login_failed;
    }

    public void setNumber_of_login_failed(Integer number_of_login_failed) {
        this.number_of_login_failed = number_of_login_failed;
    }
}
