package dev.naman.productservicettsevening.client;

import dev.naman.productservicettsevening.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeStoreCategoryClient {

    public FakeStoreCategoryClient client;

    public FakeStoreCategoryClient(FakeStoreCategoryClient client){
        this.client=client;
    }

    public String[] getAllCategories() {
        return client.getAllCategories();
    }

    public Optional<List<ProductDto>> getProductsInCategory(String categoryName) {
        return client.getProductsInCategory(categoryName);
    }
}
