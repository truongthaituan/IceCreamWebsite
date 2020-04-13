package com.example.demo.dto;

public class SendMail {
    String userName;
    String email;
    String address;
    String phone;
    String title;
    String quantity;
    String price;
    String subTotal;
    String total;
    String createDate;

    public SendMail(String userName, String email, String address, String phone, String title,
                    String quantity, String price, String subTotal, String total, String createDate) {
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
        this.total = total;
        this.createDate = createDate;
    }

    public SendMail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
