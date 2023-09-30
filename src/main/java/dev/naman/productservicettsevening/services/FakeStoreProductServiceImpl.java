package dev.naman.productservicettsevening.services;

import dev.naman.productservicettsevening.client.FakeStoreProductClient;
import dev.naman.productservicettsevening.dtos.ProductDto;
import dev.naman.productservicettsevening.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {


    private FakeStoreProductClient client;

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreProductClient client){
        this.client=client;
    }
    @Override
    public List<ProductDto> getAllProducts() {
        return client.getAllProducts();
    }

    @Override
    public Optional<ProductDto> getSingleProduct(Long productId) {
        return client.getSingleProduct(productId);

    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        return client.addNewProduct(productDto);
    }

    @Override
    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        return client.updateProduct(productId,productDto);
    }

    @Override
    public ProductDto replaceProduct(Long productId,ProductDto productDto) {
        return client.replaceProduct(productId,productDto);
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        return client.deleteProduct(productId);
    }
}
