package dev.naman.productservicettsevening.services;

import dev.naman.productservicettsevening.dtos.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
