package com.traore.stockmanagement.service;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.category.CategoryDetailsDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
    CategoryDetailsDTO getCategoryDetail(Long categoryId);
    void delete(Long categoryId);
}
