package com.restaurantapp.menuservice.serivce;

import com.restaurantapp.menuservice.exception.IncorrectDataException;
import com.restaurantapp.menuservice.exception.NotFoundException;
import com.restaurantapp.menuservice.mapper.MenuMapper;
import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.model.entity.Dish;
import com.restaurantapp.menuservice.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final CategoryService categoryService;

    @Transactional
    public DishDto addDish(DishDto dishDto) {
        if (menuRepository.getByName(dishDto.getName()).isPresent()) {
            throw new IncorrectDataException("Dish with given name already exist");
        }
        Dish dish = menuMapper.toEntity(dishDto);
        dish.setCategory(categoryService.findOrCreateByName(dish.getCategory().getName()));
        menuRepository.save(dish);
        log.info("Dish {} added to database", dish);
        return menuMapper.toDto(dish);
    }

    public List<DishDto> getMenu(String category, String dishName) {
        List<Dish> categoryList = (category != null && !category.isBlank())
                ? menuRepository.getByCategory(category)
                : menuRepository.findAll();
        List<Dish> dishList = (dishName != null && !dishName.isBlank())
                ? menuRepository.getByName(dishName).map(List::of).orElseGet(List::of)
                : menuRepository.findAll();
        List<String> namesDishList = dishList.stream()
                .map(Dish::getName)
                .toList();

        return categoryList.stream()
                .filter(dish -> namesDishList.contains(dish.getName()))
                .map(menuMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DishDto> getRandomDishes(Integer amount) {
        if(amount <= 0) {
            throw new IncorrectDataException("Incorrect dish amount");
        }
        List<Dish> dishList = menuRepository.findAll();
        Collections.shuffle(dishList);
        return dishList.subList(0, Math.min(amount, dishList.size())).stream()
                .map(menuMapper::toDto)
                .toList();
    }

    @Transactional
    public DishDto updateDish(DishDto updatedDish) {
        Dish dish = menuRepository.getByName(updatedDish.getName())
                .orElseThrow(() -> new IncorrectDataException("Dish with given name don't exist"));
        dish.update(menuMapper.toEntity(updatedDish));
        dish.setCategory(categoryService.findOrCreateByName(dish.getCategory().getName()));
        dish.setName(updatedDish.getName());
        menuRepository.save(dish);
        log.info("Dish {} got updated", dish);
        return menuMapper.toDto(dish);
    }

    public void deleteDish(String dishName) {
        Dish dish = menuRepository.getByName(dishName)
                .orElseThrow(() -> new NotFoundException("Dish with given name don't exist"));
        menuRepository.delete(dish);
        log.info("Dish {} got deleted from database", dish);
    }
}
