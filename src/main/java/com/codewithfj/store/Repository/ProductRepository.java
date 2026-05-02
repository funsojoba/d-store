package com.codewithfj.store.Repository;

import com.codewithfj.store.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
//    Optional<Product> findById(Long id);
    List<Product> findByCategoryId(Long categoryId);
}
