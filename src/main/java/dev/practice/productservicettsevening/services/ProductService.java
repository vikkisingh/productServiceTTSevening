package dev.practice.productservicettsevening.services;

import dev.practice.productservicettsevening.dtos.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getAllProducts();

    Optional<ProductDto> getSingleProduct(Long productId);

    ProductDto addNewProduct(ProductDto productDto);

    ProductDto updateProduct(Long productId,ProductDto productDto);

    ProductDto replaceProduct(Long productId,ProductDto productDto);

    ProductDto deleteProduct(Long productId);
}
