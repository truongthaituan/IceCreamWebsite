package com.example.demo.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    user user;
    @ManyToOne
    @JoinColumn(name = "icecream_id")
    icecream icecream;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Float price;
    @Column(name = "status")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean status;
    @Column(name = "viewcount")
    private Integer view_count;
    @Column(name = "image")
    private String image;
    @Column(name = "details")
    private String details;
    @Column(name = "upload_date")
    private Date upload_date;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    Set<order_details> order_details;

    public recipe(user user, icecream icecream, String title, String description,
                  Float price, Boolean status, Integer view_count, String image, String details, Date upload_date) {
        this.user = user;
        this.icecream = icecream;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.view_count = view_count;
        this.image = image;
        this.details = details;
        this.upload_date = upload_date;
    }

    public recipe() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public icecream getIcecream() {
        return icecream;
    }

    public void setIcecream(icecream icecream) {
        this.icecream = icecream;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }
}
