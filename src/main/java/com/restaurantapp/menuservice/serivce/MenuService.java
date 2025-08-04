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
        if(menuRepository.getByName(dishDto.getName()) == null) {
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
                ? List.of(menuRepository.getByName(dishName))
                : menuRepository.findAll();

        return categoryList.stream()
                .filter(dishList::contains)
                .map(menuMapper::toDto)
                .collect(Collectors.toList());
    }

    public DishDto updateDish(String dishName, DishDto updatedDish) {
        Dish dish = menuRepository.getByName(dishName);
        if(dish == null) {
            throw new IncorrectDataException("Dish with given name don't exist");
        }
        dish.update(menuMapper.toEntity(updatedDish));
        dish.setName(dishName);
        menuRepository.save(dish);
        return menuMapper.toDto(dish);
    }

    public void deleteDish(String dishName) {
        Dish dish = menuRepository.getByName(dishName);
        if(dish == null || dish.equals(new Dish())) {
            throw new NotFoundException("Dish with given name don't exist");
        }
        menuRepository.delete(dish);
    }
}
