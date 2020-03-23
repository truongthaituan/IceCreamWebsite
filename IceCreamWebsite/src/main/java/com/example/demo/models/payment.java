package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment")
public class payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long payment_id;
    @Column(name = "card_type")
    private String card_type;
    @Column(name = "card_number")
    private Long card_number;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "name_on_card")
    private String name_on_card;
    @Column(name = "expired_date")
    private Date expired_date;
    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payment")
    private Set<order> orders = new HashSet<>();

    public payment(String card_type, Long card_number, String cvv, String name_on_card, Date expired_date, Date date_of_birth) {
        this.card_type = card_type;
        this.card_number = card_number;
        this.cvv = cvv;
        this.name_on_card = name_on_card;
        this.expired_date = expired_date;
        this.date_of_birth = date_of_birth;
    }

    public payment() {
    }

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public Long getCard_number() {
        return card_number;
    }

    public void setCard_number(Long card_number) {
        this.card_number = card_number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getName_on_card() {
        return name_on_card;
    }

    public void setName_on_card(String name_on_card) {
        this.name_on_card = name_on_card;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
