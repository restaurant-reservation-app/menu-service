package com.restaurantapp.menuservice.mapper;

import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.model.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "photo", source = "photoBase64", qualifiedByName = "stringToBytes")
    Dish toEntity(DishDto dto);

    @Mapping(target = "photoBase64", source = "photo", qualifiedByName = "bytesToString")
    DishDto toDto(Dish entity);

    @Named("stringToBytes")
    static byte[] stringToBytes(String base64) {
        return base64 != null ? Base64.getDecoder().decode(base64) : null;
    }

    @Named("bytesToString")
    static String bytesToString(byte[] bytes) {
        return bytes != null ? Base64.getEncoder().encodeToString(bytes) : null;
    }
}
