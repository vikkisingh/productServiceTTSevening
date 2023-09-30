package dev.naman.productservicettsevening.controllers;

import dev.naman.productservicettsevening.dtos.ProductDto;
import dev.naman.productservicettsevening.exception.IdNotFoundException;
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
import java.util.Optional;


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
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("productId") Long productId) throws IdNotFoundException {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        Optional<ProductDto> singleProduct = productService.getSingleProduct(productId);
        if(singleProduct.isEmpty())
            throw new IdNotFoundException("Product not found with id "+ productId);

        ResponseEntity<ProductDto> response = new ResponseEntity(
                singleProduct.get(),
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

    @PatchMapping ("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductDto productDto) {
        ProductDto product=productService.updateProduct(productId,productDto);
        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @PutMapping ("/replace/{productId}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("productId") Long productId,@RequestBody ProductDto productDto) {
        ProductDto product=productService.updateProduct(productId,productDto);
        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId") Long productId) {
        ProductDto product = productService.deleteProduct(productId);
        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }
}
