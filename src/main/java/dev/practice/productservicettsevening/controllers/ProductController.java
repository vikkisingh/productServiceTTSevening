package dev.practice.productservicettsevening.controllers;

import dev.practice.productservicettsevening.dtos.ProductDto;
import dev.practice.productservicettsevening.exception.IdNotFoundException;
import dev.practice.productservicettsevening.models.Product;
import dev.practice.productservicettsevening.repository.ProductRepository;
import dev.practice.productservicettsevening.services.ProductService;
import dev.practice.productservicettsevening.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    private ProductRepository productRepository;

    private ProductService productServiceImpl;

    public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService, ProductRepository productRepository,@Qualifier("productServiceImpl") ProductService productServiceImpl) {
        this.productService = productService;
        this.productRepository=productRepository;
        this.productServiceImpl=productServiceImpl;
    }

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productServiceImpl.getAllProducts();

       // return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("productId") Long productId) throws IdNotFoundException {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        Optional<ProductDto> singleProduct = productServiceImpl.getSingleProduct(productId);
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
        //ProductDto product=productService.addNewProduct(productDto);

        //Product newProduct=new Product();
        //newProduct.setTitle(product.getTitle());
        //newProduct.setPrice(product.getPrice());
       // newProduct.setDescription(product.getDescription());
        //newProduct.setImageUrl(product.getImage());
        //newProduct.setCreatedAt(new Date());
        //productRepository.save(newProduct);
        ProductDto product=productServiceImpl.addNewProduct(productDto);

        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @PatchMapping ("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductDto productDto) {
        ProductDto product=productServiceImpl.updateProduct(productId,productDto);
        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @PutMapping ("/replace/{productId}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("productId") Long productId,@RequestBody ProductDto productDto) {
        ProductDto product=productServiceImpl.updateProduct(productId,productDto);
        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId") Long productId) {
        //ProductDto product = productService.deleteProduct(productId);
        ProductDto product = productServiceImpl.deleteProduct(productId);
        ResponseEntity<ProductDto> response=new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }
}
