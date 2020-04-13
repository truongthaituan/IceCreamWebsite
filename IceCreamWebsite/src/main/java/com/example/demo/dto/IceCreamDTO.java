package com.example.demo.dto;

public class IceCreamDTO {
    private Long icecreamId;
    private String name;
    private String description;

    public IceCreamDTO(Long icecreamId, String name, String description) {
        this.icecreamId = icecreamId;
        this.name = name;
        this.description = description;
    }

    public IceCreamDTO() {
    }

    public Long getIcecreamId() {
        return icecreamId;
    }

    public void setIcecreamId(Long icecreamId) {
        this.icecreamId = icecreamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
