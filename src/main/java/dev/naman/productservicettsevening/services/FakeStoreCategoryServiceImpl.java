package dev.naman.productservicettsevening.services;

import dev.naman.productservicettsevening.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FakeStoreCategoryServiceImpl implements CategoryService {

    private RestTemplateBuilder restTemplateBuilder;

    public  FakeStoreCategoryServiceImpl(RestTemplateBuilder builder){
        this.restTemplateBuilder=builder;
    }

    @Override
    public String[] getAllCategories() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<String[]> dtoList=restTemplate.getForEntity("https://fakestoreapi.com/products/categories",String[].class);
        return dtoList.getBody();
    }

    @Override
    public Optional<List<ProductDto>> getProductsInCategory(String categoryName) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> dtoList=
                restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryName}", ProductDto[].class,categoryName);
        List<ProductDto> answer=new ArrayList<>();
        if(dtoList.getBody()!=null || dtoList.getBody().length!=0){
            for(ProductDto dto:dtoList.getBody()){
                ProductDto product=new ProductDto();
                product.setId(dto.getId());
                product.setTitle(dto.getTitle());
                product.setDescription(dto.getDescription());
                product.setPrice(dto.getPrice());
                product.setImage(dto.getImage());
                product.setCategory(dto.getCategory());
                answer.add(product);
            }
        }
        if(answer.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(answer);
    }
}
