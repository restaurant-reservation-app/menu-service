package com.restaurantapp.menuservice.serivce;

import com.restaurantapp.menuservice.model.entity.Category;
import com.restaurantapp.menuservice.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Transactional
    public Category findOrCreateByName(String name) {
        return categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(name);
                    category.setSequence(categoryRepository.findAll().size() + 1);
                    categoryRepository.save(category);
                    log.info("Created new category = {}", category);
                    return category;
                });
    }

    public List<String> getCategories() {
        return categoryRepository.findAllNamesOrderedByOrder();
    }
}
