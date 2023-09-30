package dev.naman.productservicettsevening.client;

import dev.naman.productservicettsevening.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreProductClient {
    public RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    public List<ProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> dtoList=restTemplate.getForEntity(
                "https://fakestoreapi.com/products", ProductDto[].class);
        List<ProductDto> answer = new ArrayList<>();

        if(dtoList.getBody()!=null){
            for(ProductDto dto:dtoList.getBody()){
                ProductDto product=new ProductDto();
                product.setId(dto.getId());
                product.setTitle(dto.getTitle());
                product.setDescription(dto.getDescription());
                product.setPrice(dto.getPrice());
                product.setImage(dto.getImage());
                product.setCategory(dto.getCategory());
                answer.add(product);
            }
        }

        return answer;
    }


    public Optional<ProductDto> getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto>  response=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId);

        if(response.getBody() == null){
            return Optional.empty();
        }
        return Optional.of(response.getBody());
    }


    public ProductDto addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);

        return response.getBody();
    }

    /*@Override
    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //ProductDto productDto1 = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productId);
        //restTemplate.put("https://fakestoreapi.com/products/{id}",ProductDto.class,productId);
        restTemplate.put("https://fakestoreapi.com/products/{id}",productDto,productId);
        productDto.setId(productId);
        return productDto;
    }*/


    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        ResponseEntity<ProductDto> response = requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productId);
        return response.getBody();
    }


    public ProductDto replaceProduct(Long productId,ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        ResponseEntity<ProductDto> response = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productId);
        return response.getBody();
    }


    public ProductDto deleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        ResponseEntity<ProductDto> response = requestForEntity(HttpMethod.DELETE, "https://fakestoreapi.com/products/{id}", null, ProductDto.class, productId);
        return response.getBody();
        //restTemplate.delete("https://fakestoreapi.com/products/{id}",productId);
        // return null;
    }
}
