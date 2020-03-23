package com.example.demo.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    customer customer;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    payment payment;
    @Column(name = "payment_option")
    private String payment_option;
    @Column(name = "create_date")
    private Date create_date;
    @Column(name = "delivery_detail")
    private String delivery_detail;
    @Column(name = "notes")
    private String notes;
    @Column(name = "status")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    Set<order_details> order_details;

    public order(customer customer, String payment_option,
                 payment payment, Date create_date, String delivery_detail, String notes, Boolean status) {
        this.customer = customer;
        this.payment_option = payment_option;
        this.payment = payment;
        this.create_date = create_date;
        this.delivery_detail = delivery_detail;
        this.notes = notes;
        this.status = status;
    }

    public order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public customer getCustomer() {
        return customer;
    }

    public void setCustomer(customer customer) {
        this.customer = customer;
    }

    public String getPayment_option() {
        return payment_option;
    }

    public void setPayment_option(String payment_option) {
        this.payment_option = payment_option;
    }

    public payment getPayment() {
        return payment;
    }

    public void setPayment(payment payment) {
        this.payment = payment;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getDelivery_detail() {
        return delivery_detail;
    }

    public void setDelivery_detail(String delivery_detail) {
        this.delivery_detail = delivery_detail;
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
