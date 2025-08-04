package com.restaurantapp.menuservice.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DishDto {
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private String photoBase64;
}
