package com.example.demo.dto;

import java.util.Date;

public class RecipeDTO {
    private Long id;
    private Long userId;
    private Long icecreamId;
    private String title;
    private String description;
    private Float price;
    private Boolean status;
    private Integer viewCount;
    private String image;
    private String details;
    private Date uploadDate;

    public RecipeDTO(Long id, Long userId, Long icecreamId, String title, String description, Float price,
                     Boolean status, Integer viewCount, String image, String details, Date uploadDate) {
        this.id = id;
        this.userId = userId;
        this.icecreamId = icecreamId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.viewCount = viewCount;
        this.image = image;
        this.details = details;
        this.uploadDate = uploadDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIcecreamId() {
        return icecreamId;
    }

    public void setIcecreamId(Long icecreamId) {
        this.icecreamId = icecreamId;
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
