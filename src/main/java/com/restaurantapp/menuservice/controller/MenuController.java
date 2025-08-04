package com.restaurantapp.menuservice.controller;

import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.serivce.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menu", produces = "application/json")
public class MenuController {
    private final MenuService menuService;

    @Operation(summary = "Create new dish in menu with unique dish name")
    @PostMapping("/dish/add")
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto addDish(@RequestBody DishDto dishDto) {
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
        return menuService.getMenu(category, dishName);
    }

    @Operation(summary = "Get random dishes from menu with given amount")
    @GetMapping("/random")
    public List<DishDto> getRandomDishes(@RequestParam(required = false, defaultValue = "5") int amount) {
        return menuService.getRandomDishes(amount);
    }

    @Operation(summary = "Update dish with given name")
    @PatchMapping("/dish/update")
    public DishDto updateDish(
            @RequestBody DishDto updatedDish) {
        return menuService.updateDish(updatedDish);
    }

    @Operation(summary = "Delete dish with given name")
    @DeleteMapping("/dish/delete/{dishName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable String dishName) {
        menuService.deleteDish(dishName);
    }
}
