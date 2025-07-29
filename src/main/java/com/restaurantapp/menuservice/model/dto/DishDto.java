package com.restaurantapp.menuservice.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class DishDto {
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private MultipartFile photo;
}
