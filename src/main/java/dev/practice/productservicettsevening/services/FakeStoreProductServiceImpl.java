package dev.practice.productservicettsevening.services;

import dev.practice.productservicettsevening.client.FakeStoreProductClient;
import dev.practice.productservicettsevening.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "fakeStoreProductServiceImpl")
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
