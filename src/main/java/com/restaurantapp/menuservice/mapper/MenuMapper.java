package com.restaurantapp.menuservice.mapper;

import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.model.entity.Dish;
import com.restaurantapp.menuservice.utils.ByteArrayMultipartFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "photo", source = "photo")
    Dish toEntity(DishDto dto);

    @Mapping(target = "photo", source = "photo")
    DishDto toDto(Dish entity);

    default byte[] map(MultipartFile file) {
        if(file==null || file.isEmpty()) {
            return null;
        }
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default MultipartFile map(byte[] bytes) {
        if(bytes == null || bytes.length == 0) {
            return null;
        }
        return new ByteArrayMultipartFile(bytes, "photo.jpg");
    }
}
