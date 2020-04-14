package com.example.demo.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class Order_details {
    //    @EmbeddedId
//    Order_DetailsKey id;
    @Id
    @Column(name = "order_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailsId;
    @ManyToOne
//    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    Order order;
    @ManyToOne
//    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Float price;


    public Order_details(Order order, Recipe recipe, Integer quantity, Float price) {
        this.order = order;
        this.recipe = recipe;
        this.quantity = quantity;
        this.price = price;
    }

    public Order_details() {
    }
    public Long getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Long orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
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

}

//@Embeddable
//class Order_DetailsKey implements Serializable {
//
//    @Column(name = "order_id", nullable = false)
//    Long order_id;
//
//    @Column(name = "recipe_id", nullable = false)
//    Long recipe_id;
//
//    public Order_DetailsKey(Long order_id, Long recipe_id) {
//        this.order_id = order_id;
//        this.recipe_id = recipe_id;
//    }
//
//    public Order_DetailsKey() {
//    }
//
//    public Long getOrder_id() {
//        return order_id;
//    }
//
//    public void setOrder_id(Long order_id) {
//        this.order_id = order_id;
//    }
//
//    public Long getRecipe_id() {
//        return recipe_id;
//    }
//
//    public void setRecipe_id(Long recipe_id) {
//        this.recipe_id = recipe_id;
//    }
//    // standard constructors, getters, and setters
//    // hashcode and equals implementation
//}
