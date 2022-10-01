package com.devluis.rubberstore.repository;

import com.devluis.rubberstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);
}
