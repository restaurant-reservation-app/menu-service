package com.restaurantapp.menuservice.mapper;

import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.model.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "photo", source = "photoBase64")
    Dish toEntity(DishDto dto);

    @Mapping(target = "photoBase64", source = "photo")
    DishDto toDto(Dish entity);

}
