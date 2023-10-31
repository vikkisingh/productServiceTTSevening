package dev.practice.productservicettsevening.repository;

import dev.practice.productservicettsevening.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAll();

    Optional<Category> findCategoryByName(String categoryName);
}
