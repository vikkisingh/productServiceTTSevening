package dev.practice.productservicettsevening.services;

import dev.practice.productservicettsevening.dtos.ProductDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    String[] getAllCategories();

    Optional<List<ProductDto>> getProductsInCategory(String categoryName);
}
