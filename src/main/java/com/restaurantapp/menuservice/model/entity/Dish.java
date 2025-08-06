package com.restaurantapp.menuservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@Table(name = "DISH")
public class Dish {
    @Id
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "PHOTO")
    private byte[] photo;

    public void update(Dish dish) {
        if(dish.getName() != null) {
            this.name = dish.getName();
        }
        if(dish.getDescription() != null) {
            this.description = dish.getDescription();
        }
        if(dish.getCategory() != null) {
            this.category = dish.getCategory();
        }
        if(dish.getPrice() != null) {
            this.price = dish.getPrice();
        }
        if(dish.getPhoto() != null) {
            this.photo = dish.getPhoto();
        }
    }
}
