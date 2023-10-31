package dev.practice.productservicettsevening.controllers;

import dev.practice.productservicettsevening.dtos.ProductDto;
import dev.practice.productservicettsevening.exception.CategoryNotFoundException;
import dev.practice.productservicettsevening.services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private CategoryService categoryServiceImpl;

    public CategoryController(@Qualifier("fakeStoreCategoryServiceImpl") CategoryService categoryService,
                              @Qualifier("categoryServiceImpl") CategoryService categoryServiceImpl) {
        this.categoryService = categoryService;
        this.categoryServiceImpl=categoryServiceImpl;
    }

    @GetMapping("/categories")
    public ResponseEntity<String[]> getAllCategories() {
        return new ResponseEntity<>(categoryServiceImpl.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws CategoryNotFoundException {
        Optional<List<ProductDto>> productsInCategory = categoryServiceImpl.getProductsInCategory(categoryName);
        if(productsInCategory.isEmpty()){
            throw new CategoryNotFoundException("Category Not found with name "+categoryName);
        }
        return new ResponseEntity<>(productsInCategory.get(), HttpStatus.OK);
    }
}
