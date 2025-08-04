package com.restaurantapp.menuservice.service;

import com.restaurantapp.menuservice.exception.IncorrectDataException;
import com.restaurantapp.menuservice.exception.NotFoundException;
import com.restaurantapp.menuservice.mapper.MenuMapper;
import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.model.entity.Dish;
import com.restaurantapp.menuservice.repository.MenuRepository;
import com.restaurantapp.menuservice.serivce.MenuService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class MenuServiceTest {
    private MenuService menuService;
    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        this.menuRepository = Mockito.mock(MenuRepository.class);
        MenuMapper menuMapper = Mappers.getMapper(MenuMapper.class);
        menuService = new MenuService(menuMapper, menuRepository);
    }

    @ParameterizedTest
    @MethodSource
    void getMenuTest(String category, String dishName, List<Dish> categoryList, Optional<Dish> dish, List<Dish> allDishes, List<DishDto> expectedResult) {
        when(menuRepository.getByCategory(category)).thenReturn(categoryList);
        when(menuRepository.findAll()).thenReturn(allDishes);
        when(menuRepository.getByName(dishName)).thenReturn(dish);

        List<DishDto> result = menuService.getMenu(category, dishName);

        Assertions.assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> getMenuTest() {
        Dish dish = getDish("name1");
        List<Dish> categoryList1 = List.of(
                getDish("name1"),
                getDish("name2"),
                getDish("name3")
        );
        List<Dish> categoryList2 = List.of(
                getDish("name4"),
                getDish("name5"),
                getDish("name6")
        );
        List<Dish> allDishes = List.of(
                getDish("name1"),
                getDish("name2"),
                getDish("name3"),
                getDish("name4"),
                getDish("name5"),
                getDish("name6"),
                getDish("name7"),
                getDish("name8")
        );
        DishDto dishDto = getDishDto("name1");
        List<DishDto> categoryList1Dto = List.of(
                getDishDto("name1"),
                getDishDto("name2"),
                getDishDto("name3")
        );
        List<DishDto> categoryList2Dto = List.of(
                getDishDto("name4"),
                getDishDto("name5"),
                getDishDto("name6")
        );
        List<DishDto> allDishesDto = List.of(
                getDishDto("name1"),
                getDishDto("name2"),
                getDishDto("name3"),
                getDishDto("name4"),
                getDishDto("name5"),
                getDishDto("name6"),
                getDishDto("name7"),
                getDishDto("name8")
        );
        return Stream.of(
                Arguments.of(null, null, categoryList1, Optional.of(dish), allDishes, allDishesDto),
                Arguments.of(null, null, List.of(), Optional.empty(), List.of(), List.of()),
                Arguments.of("category", null, categoryList1, Optional.of(dish), allDishes, categoryList1Dto),
                Arguments.of("category", null, categoryList2, Optional.of(dish), allDishes, categoryList2Dto),
                Arguments.of("category", null, List.of(), Optional.of(dish), allDishes, List.of()),
                Arguments.of(null, "name", categoryList1, Optional.of(dish), allDishes, List.of(dishDto)),
                Arguments.of(null, "name", categoryList1, Optional.empty(), allDishes, List.of()),
                Arguments.of("category", "name", categoryList1, Optional.of(dish), allDishes, List.of(dishDto)),
                Arguments.of("category", "name", categoryList2, Optional.of(dish), allDishes, List.of()),
                Arguments.of("category", "name", categoryList1, Optional.empty(), allDishes, List.of()),
                Arguments.of("category", "name", categoryList2, Optional.empty(), allDishes, List.of()),
                Arguments.of("category", "name", List.of(), Optional.empty(), allDishes, List.of()),
                Arguments.of("category", "name", List.of(), Optional.of(dish), allDishes, List.of())
        );
    }

    @Test
    void addDish_CorrectData_DtoReturned() {
        DishDto dishDto = getDishDto("name");
        when(menuRepository.getByName("name")).thenReturn(Optional.empty());

        DishDto result = menuService.addDish(dishDto);

        Assertions.assertEquals(dishDto, result);
    }

    @Test
    void addDish_IncorrectData_ExceptionThrown() {
        DishDto dishDto = getDishDto("name");
        Dish dish = getDish("name");
        when(menuRepository.getByName("name")).thenReturn(Optional.of(dish));

        var exception = Assertions.assertThrows(IncorrectDataException.class,
                () -> menuService.addDish(dishDto));

        Assertions.assertEquals("Dish with given name already exist", exception.getMessage());
    }

    @Test
    void updateDish_noDishInRepository_IncorrectDataExceptionThrown() {
        when(menuRepository.getByName("name")).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(IncorrectDataException.class,
                () -> menuService.updateDish("name", getDishDto("name")));

        Assertions.assertEquals("Dish with given name don't exist", exception.getMessage());
    }

    @Test
    void updateDish_correctApproach_DtoReturned() {
        String name = "name";
        Dish dish = getDish(name);
        DishDto dishDto = getDishDto(name);

        when(menuRepository.getByName(name)).thenReturn(Optional.of(dish));

        DishDto result = menuService.updateDish(name, dishDto);

        Assertions.assertEquals(dishDto, result);
    }

    @Test
    void deleteDish_dishInDatabase_dishDeleted() {
        Dish dish = getDish("name");
        when(menuRepository.getByName("name")).thenReturn(Optional.of(dish));

        menuService.deleteDish("name");

        verify(menuRepository, times(1)).delete(dish);
    }

    @Test
    void deleteDish_noDishInDatabase_NotFoundExceptionThrown() {
        when(menuRepository.getByName("name")).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(NotFoundException.class,
                () -> menuService.deleteDish("name"));

        Assertions.assertEquals("Dish with given name don't exist", exception.getMessage());
    }

    static Dish getDish(String name) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDescription("description");
        dish.setCategory("category");
        dish.setPrice(new BigDecimal("12.5"));
        return dish;
    }

    static DishDto getDishDto(String name) {
        return DishDto.builder()
                .name(name)
                .description("description")
                .category("category")
                .price(new BigDecimal("12.5"))
                .build();
    }
}
