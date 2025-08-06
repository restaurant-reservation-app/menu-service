package com.restaurantapp.menuservice.repository;

import com.restaurantapp.menuservice.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Dish, Long> {
    @Query(
            value = "SELECT * FROM DISH d WHERE d.name = ?1",
            nativeQuery = true
    )
    Optional<Dish> getByName(String name);

    @Query(value = """
            SELECT d.* FROM DISH d
            JOIN CATEGORY c ON d.category_id = c.id
            WHERE c.name = ?1
            """, nativeQuery = true)
    List<Dish> getByCategory(String name);
}
