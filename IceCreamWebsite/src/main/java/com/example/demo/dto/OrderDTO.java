package com.example.demo.dto;

import com.example.demo.models.Customer;
import com.example.demo.models.Payment;

import java.util.Date;

public class OrderDTO {
    private Long id;

    private Customer customer;

    private Payment payment;

    private String paymentOption;

    private Date createDate;

    private String deliveryDetail;

    private String notes;

    private Boolean status;

    public OrderDTO(Long id, Customer customer, Payment payment, String paymentOption,
                    Date createDate, String deliveryDetail, String notes, Boolean status) {
        this.id = id;
        this.customer = customer;
        this.payment = payment;
        this.paymentOption = paymentOption;
        this.createDate = createDate;
        this.deliveryDetail = deliveryDetail;
        this.notes = notes;
        this.status = status;
    }

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeliveryDetail() {
        return deliveryDetail;
    }

    public void setDeliveryDetail(String deliveryDetail) {
        this.deliveryDetail = deliveryDetail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
