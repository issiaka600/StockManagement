package com.traore.stockmanagement.controller;

import com.traore.stockmanagement.controller.api.CategoryAPI;
import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.category.CategoryDetailsDTO;
import com.traore.stockmanagement.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CategoryController implements CategoryAPI {
    private CategoryService categoryService;

    @Override
    public CategoryDTO save(CategoryDTO dto) {
        return categoryService.save(dto);
    }

    @Override
    public CategoryDTO update(CategoryDTO dto) {
        return categoryService.update(dto);
    }

    @Override
    public CategoryDetailsDTO getCategoryDetail(Long categoryId) {
        return categoryService.getCategoryDetail(categoryId);
    }

    @Override
    public void delete(Long categoryId) {
        categoryService.delete(categoryId);
    }
}
