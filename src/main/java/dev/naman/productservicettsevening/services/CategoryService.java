package dev.naman.productservicettsevening.services;

import dev.naman.productservicettsevening.dtos.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    String[] getAllCategories();

    Optional<List<ProductDto>> getProductsInCategory(String categoryName);
}
