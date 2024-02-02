package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,String> {
    List<Product> findProductsByCategory_Id(Long categoryId);
}
