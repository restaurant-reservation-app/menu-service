package com.restaurantapp.menuservice.serivce;

import com.restaurantapp.menuservice.exception.IncorrectDataException;
import com.restaurantapp.menuservice.exception.NotFoundException;
import com.restaurantapp.menuservice.mapper.MenuMapper;
import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.model.entity.Dish;
import com.restaurantapp.menuservice.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    public DishDto addDish(DishDto dishDto) {
        if (menuRepository.getByName(dishDto.getName()).isPresent()) {
            throw new IncorrectDataException("Dish with given name already exist");
        }
        Dish dish = menuMapper.toEntity(dishDto);
        menuRepository.save(dish);
        return menuMapper.toDto(dish);
    }

    public List<DishDto> getMenu(String category, String dishName) {
        List<Dish> categoryList = (category != null && !category.isBlank())
                ? menuRepository.getByCategory(category)
                : menuRepository.findAll();
        List<Dish> dishList = (dishName != null && !dishName.isBlank())
                ? menuRepository.getByName(dishName).map(List::of).orElseGet(List::of)
                : menuRepository.findAll();

        return categoryList.stream()
                .filter(dishList::contains)
                .map(menuMapper::toDto)
                .collect(Collectors.toList());
    }

    public DishDto updateDish(DishDto updatedDish) {
        Dish dish = menuRepository.getByName(updatedDish.getName())
                .orElseThrow(() -> new IncorrectDataException("Dish with given name don't exist"));
        dish.update(menuMapper.toEntity(updatedDish));
        dish.setName(updatedDish.getName());
        menuRepository.save(dish);
        return menuMapper.toDto(dish);
    }

    public void deleteDish(String dishName) {
        Dish dish = menuRepository.getByName(dishName)
                .orElseThrow(() -> new NotFoundException("Dish with given name don't exist"));
        menuRepository.delete(dish);
    }
}
