package dev.naman.productservicettsevening.services;

import dev.naman.productservicettsevening.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public List<ProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> dtoList=restTemplate.getForEntity(
                "https://fakestoreapi.com/products", ProductDto[].class);
        List<ProductDto> answer = new ArrayList<>();

        if(dtoList.getBody()!=null){
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

        return answer;
    }

    @Override
    public ProductDto getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto>  response=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId);

        return response.getBody();
    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);

        return response.getBody();
    }

    @Override
    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //ProductDto productDto1 = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productId);
        //restTemplate.put("https://fakestoreapi.com/products/{id}",ProductDto.class,productId);
        restTemplate.put("https://fakestoreapi.com/products/{id}",productDto,productId);
        productDto.setId(productId);
        return productDto;
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}",productId);
        return null;
    }
}
