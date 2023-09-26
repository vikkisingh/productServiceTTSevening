package dev.naman.productservicettsevening.controllers;

import dev.naman.productservicettsevening.dtos.ProductDto;
import dev.naman.productservicettsevening.models.ProductOutputBean;
import dev.naman.productservicettsevening.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    public ProductController( ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductDto> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("productId") Long productId) {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        ResponseEntity<ProductDto> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.OK
        );
        return response;
    }


    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto) {
        ProductDto product=productService.addNewProduct(productDto);

        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductDto productDto) {
        ProductDto product=productService.updateProduct(productId,productDto);
        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return "SUCCESS";
    }
}
