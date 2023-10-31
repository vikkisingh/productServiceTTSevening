package dev.practice.productservicettsevening.services;

import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;
import dev.practice.productservicettsevening.dtos.ProductDto;
import dev.practice.productservicettsevening.exception.IdNotFoundException;
import dev.practice.productservicettsevening.models.Product;
import dev.practice.productservicettsevening.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service(value = "productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductDto> productDtos=new ArrayList<>();
        for(Product product:products){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }
    public ProductDto getProductDto(Product product){
        ProductDto dto=new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImageUrl());
        if(product.getCategory()!=null){
            dto.setCategory(product.getCategory().getName());
        }
        return dto;
    }

    @Override
    public Optional<ProductDto> getSingleProduct(Long productId) {
        Optional<Product> productOp=productRepository.findProductById(productId);
        return productOp.map(this::getProductDto);
    }

    public Product getProduct(ProductDto productDto){
        Product product=new Product();
        /*if(productDto.getId()!=null){
            product.setId(productDto.getId());
        }*/
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(null);
        product.setImageUrl(productDto.getImage());
        return product;
    }
    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        Product product=getProduct(productDto);
        product= productRepository.save(product);
        return getProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product=getProduct(productDto);
        product.setId(productId);
        return getProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto replaceProduct(Long productId, ProductDto productDto) {
        Product product=getProduct(productDto);
        product.setId(productId);
        return getProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        Optional<Product> productOp=productRepository.findProductById(productId);
        ProductDto dto=null;
        if(productOp.isPresent()){
            dto=getProductDto(productOp.get());
            productRepository.deleteById(productId);
        }
        return dto;
    }
}
