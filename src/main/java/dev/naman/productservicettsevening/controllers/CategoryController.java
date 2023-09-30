package dev.naman.productservicettsevening.controllers;

import dev.naman.productservicettsevening.dtos.ProductDto;
import dev.naman.productservicettsevening.exception.CategoryNotFoundException;
import dev.naman.productservicettsevening.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<String[]> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws CategoryNotFoundException {
        Optional<List<ProductDto>> productsInCategory = categoryService.getProductsInCategory(categoryName);
        if(productsInCategory.isEmpty()){
            throw new CategoryNotFoundException("Category Not found with name "+categoryName);
        }
        return new ResponseEntity<>(productsInCategory.get(), HttpStatus.OK);
    }
}
