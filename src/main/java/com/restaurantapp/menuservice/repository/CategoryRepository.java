package com.restaurantapp.menuservice.repository;

import com.restaurantapp.menuservice.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query(
            value = "SELECT c.name FROM CATEGORY c ORDER BY c.sequence ASC",
            nativeQuery = true
    )
    List<String> findAllNamesOrderedByOrder();
}
