package com.example.demo.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "icecream_id")
    private IceCream icecream;
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
    private Integer viewCount;
    @Column(name = "image")
    private String image;
    @Column(name = "details")
    private String details;
    @Column(name = "upload_date")
    private Date uploadDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    Set<Order_details> order_details;

    public Recipe(User user, IceCream icecream, String title, String description,
                  Float price, Boolean status, Integer viewCount, String image, String details, Date uploadDate) {
        this.user = user;
        this.icecream = icecream;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.viewCount = viewCount;
        this.image = image;
        this.details = details;
        this.uploadDate = uploadDate;
    }

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IceCream getIcecream() {
        return icecream;
    }

    public void setIcecream(IceCream icecream) {
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

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
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

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
