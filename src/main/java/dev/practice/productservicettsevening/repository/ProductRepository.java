package dev.practice.productservicettsevening.repository;

import dev.practice.productservicettsevening.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Product save(Product product);


    Optional<Product> findProductById(long productId);

    List<Product> findAll();


     void deleteById(long productId);

     List<Product> findProductByCategory_Id(long id);
}
