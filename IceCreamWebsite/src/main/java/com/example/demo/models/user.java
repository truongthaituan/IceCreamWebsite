package com.example.demo.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "status")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "avatar")
    private String avatar;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", /* Tạo ra một cái Join Table tên là "userrole" */
            joinColumns = @JoinColumn(name = "user_id"), /* Trong đó, foreign key chính là user_id trỏ tới class hiện tại (User) */
            inverseJoinColumns = @JoinColumn(name = "role_id" /* Khóa ngoại thứ 2 trỏ tới khóa chính của bảng Role */)
    )
    private Set<role> roles = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<recipe> recipes = new HashSet<>();
    public user(String userName, Boolean status, String password, String phone_number, String avatar) {
        this.userName = userName;
        this.status = status;
        this.password = password;
        this.phone_number = phone_number;
        this.avatar = avatar;

    }

    public user() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
