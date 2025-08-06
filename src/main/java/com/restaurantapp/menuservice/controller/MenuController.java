package com.restaurantapp.menuservice.controller;

import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.serivce.CategoryService;
import com.restaurantapp.menuservice.serivce.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menu", produces = "application/json")
public class MenuController {
    private final MenuService menuService;
    private final CategoryService categoryService;

    @Operation(summary = "Create new dish in menu with unique dish name")
    @PostMapping("/dish/add")
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto addDish(@RequestBody DishDto dishDto) {
        log.info("addDish invoked with dishDto = {}", dishDto);
        return menuService.addDish(dishDto);
    }

    @Operation(summary = "Get dishes from menu")
    @GetMapping
    public List<DishDto> getMenu(
            @Parameter(description = "Filter Dishes by category",
                    example = "soup")
            @RequestParam(required = false) String category,
            @Parameter(description = "Filter Dishes by name",
                    example = "pancakes")
            @RequestParam(required = false) String dishName) {
        log.info("getMenu invoked with category = {} and name = {}", category, dishName);
        return menuService.getMenu(category, dishName);
    }

    @Operation(summary = "Get random dishes from menu with given amount")
    @GetMapping("/random")
    public List<DishDto> getRandomDishes(@RequestParam(required = false, defaultValue = "5") Integer amount) {
        log.info("getRandomDishes invoked with amount = {}", amount);
        return menuService.getRandomDishes(amount);
    }

    @Operation(summary = "Get all available dish categories")
    @GetMapping("/category")
    public List<String> getAllCategories() {
        log.info("getAllCategories invoked");
        return categoryService.getCategories();
    }

    @Operation(summary = "Update dish with given name")
    @PatchMapping("/dish/update")
    public DishDto updateDish(
            @RequestBody DishDto updatedDish) {
        log.info("updateDish invoked with updatedDish = {}", updatedDish);
        return menuService.updateDish(updatedDish);
    }

    @Operation(summary = "Delete dish with given name")
    @DeleteMapping("/dish/delete/{dishName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable String dishName) {
        log.info("deleteDish invoked with dishName = {}", dishName);
        menuService.deleteDish(dishName);
    }
}
