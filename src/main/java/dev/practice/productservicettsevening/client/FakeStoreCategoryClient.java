package dev.practice.productservicettsevening.client;

import dev.practice.productservicettsevening.dtos.ProductDto;

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
