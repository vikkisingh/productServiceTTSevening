package dev.practice.productservicettsevening.services;

import dev.practice.productservicettsevening.dtos.ProductDto;
import dev.practice.productservicettsevening.models.Category;
import dev.practice.productservicettsevening.models.Product;
import dev.practice.productservicettsevening.repository.CategoryRepository;
import dev.practice.productservicettsevening.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    private ProductServiceImpl productService;


    public CategoryServiceImpl(CategoryRepository categoryRepository
            , ProductRepository productRepository,ProductServiceImpl productService){
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;
        this.productService=productService;
    }
    @Override
    public String[] getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
        String[] categoryName=new String[categories.size()];
        int index=0;
        for(Category category:categories){
            categoryName[index++]= category.getName();
        }
        return categoryName;
    }

    @Override
    public Optional<List<ProductDto>> getProductsInCategory(String categoryName) {
        Optional<Category> opt=categoryRepository.findCategoryByName(categoryName);
        if(opt.isEmpty()){
            return Optional.empty();
        }
        List<Product> products=productRepository.findProductByCategory_Id(opt.get().getId());

        List<ProductDto> dto=new ArrayList<>();

        for(Product pro:products){
            dto.add(productService.getProductDto(pro));
        }

        return Optional.of(dto);
    }
}
