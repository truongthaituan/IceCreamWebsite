package com.example.demo.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class order_details {
    @EmbeddedId
    Order_DetailsKey id;
    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("order_id")
    @JoinColumn(name = "order_id",insertable = false, updatable = false)
    order order;
    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id",insertable = false, updatable = false)
    recipe recipe;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Float price;
    @Column(name = "notes")
    private String notes;
    public order_details(order order, recipe recipe, Integer quantity, Float price, String notes) {
        this.order = order;
        this.recipe = recipe;
        this.quantity = quantity;
        this.price = price;
        this.notes = notes;
    }

    public order_details(Order_DetailsKey id, order order,
                        recipe recipe, Integer quantity, Float price, String notes) {
        this.id = id;
        this.order = order;
        this.recipe = recipe;
        this.quantity = quantity;
        this.price = price;
        this.notes = notes;
    }

    public order_details() {
    }

    public Order_DetailsKey getId() {
        return id;
    }

    public void setId(Order_DetailsKey id) {
        this.id = id;
    }

    public order getOrder() {
        return order;
    }

    public void setOrder(order order) {
        this.order = order;
    }

    public recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
@Embeddable
class Order_DetailsKey implements Serializable {

    @Column(name = "order_id", nullable = false)
    Long order_id;

    @Column(name = "recipe_id", nullable = false)
    Long recipe_id;

    public Order_DetailsKey(Long order_id, Long recipe_id) {
        this.order_id = order_id;
        this.recipe_id = recipe_id;
    }

    public Order_DetailsKey() {
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }
    // standard constructors, getters, and setters
    // hashcode and equals implementation
}
