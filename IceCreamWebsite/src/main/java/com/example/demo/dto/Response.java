package com.example.demo.dto;

public class Response {
    private String token;
    private Boolean isLoggedIn;
    private String userName;
    private String avatar;
    private String roles;

    public Response(String token, Boolean isLoggedIn, String userName, String avatar, String roles) {
        this.token = token;
        this.isLoggedIn = isLoggedIn;
        this.userName = userName;
        this.avatar = avatar;
        this.roles = roles;
    }

    public Response() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
