package com.example.demo.dto;

import com.example.demo.models.Order;

import java.util.Date;

public class FeedbackDTO {
    private Long feedbackId;
//    Customer customer;
    Order order;
    private String details;
    private Date createDate;

    public FeedbackDTO(Long feedbackId, Order order, String details, Date createDate) {
        this.feedbackId = feedbackId;
        this.order = order;
        this.details = details;
        this.createDate = createDate;
    }

    public FeedbackDTO() {
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
