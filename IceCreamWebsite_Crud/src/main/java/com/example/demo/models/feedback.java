package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feedback")
public class feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long feedback_id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    customer customer;
    @ManyToOne
    @JoinColumn(name = "order_id")
    order order;
    @Column(name = "details")
    private String details;
    @Column(name = "create_date")
    private Date create_date;

    public feedback(customer customer, order order, String details, Date create_date) {
        this.customer = customer;
        this.order = order;
        this.details = details;
        this.create_date = create_date;
    }

    public feedback() {
    }

    public Long getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(Long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public customer getCustomer() {
        return customer;
    }

    public void setCustomer(customer customer) {
        this.customer = customer;
    }

    public order getOrder() {
        return order;
    }

    public void setOrder(order order) {
        this.order = order;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
