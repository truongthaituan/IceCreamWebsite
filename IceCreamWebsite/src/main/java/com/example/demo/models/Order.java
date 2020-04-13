package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
    @Column(name = "payment_option")
    private String paymentOption;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "delivery_detail")
    private String deliveryDetail;
    @Column(name = "notes")
    private String notes;
    @Column(name = "status")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Order_details> order_details = new ArrayList<>();

    public Order(Customer customer, Payment payment, String paymentOption, Date createDate,
                 String deliveryDetail, String notes, Boolean status) {
        this.customer = customer;
        this.payment = payment;
        this.paymentOption = paymentOption;
        this.createDate = createDate;
        this.deliveryDetail = deliveryDetail;
        this.notes = notes;
        this.status = status;
    }

    public Order() {
    }

    public List<Order_details> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(List<Order_details> order_details) {
        this.order_details = order_details;
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
