package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long paymentId;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "name_on_card")
    private String nameOnCard;
    @Column(name = "expired_date")
    private Date expiredDate;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payment")
    private Set<Order> orders = new HashSet<>();

    public Payment(String cardType, Long cardNumber, String cvv, String nameOnCard,
                   Date expiredDate, Date dateOfBirth, Set<Order> orders) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
        this.expiredDate = expiredDate;
        this.dateOfBirth = dateOfBirth;
        this.orders = orders;
    }

    public Payment() {
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
