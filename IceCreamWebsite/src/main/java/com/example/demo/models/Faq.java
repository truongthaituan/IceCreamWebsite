package com.example.demo.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "faq")
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long faqId;
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;
    @Column(name = "status")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;

    public Faq(String question, String answer, Boolean status) {
        this.question = question;
        this.answer = answer;
        this.status = status;
    }
    public Faq(Long faqId,String question, String answer, Boolean status) {
        this.faqId = faqId;
        this.question = question;
        this.answer = answer;
        this.status = status;
    }

    public Faq() {
    }

    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
