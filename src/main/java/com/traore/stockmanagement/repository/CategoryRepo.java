package com.traore.stockmanagement.repository;

import com.traore.stockmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findCategoriesByEnterprise_Id(Long id);
}
