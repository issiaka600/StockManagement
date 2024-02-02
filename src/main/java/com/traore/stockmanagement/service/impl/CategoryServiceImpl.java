package com.traore.stockmanagement.service.impl;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.category.CategoryDetailsDTO;
import com.traore.stockmanagement.exception.EntityNotFoundException;
import com.traore.stockmanagement.exception.ErrorCodes;
import com.traore.stockmanagement.exception.InvalidEntityException;
import com.traore.stockmanagement.exception.InvalidOperationException;
import com.traore.stockmanagement.mapper.CategoryMapper;
import com.traore.stockmanagement.model.Category;
import com.traore.stockmanagement.repository.CategoryRepo;
import com.traore.stockmanagement.repository.EnterpriseRepo;
import com.traore.stockmanagement.service.CategoryService;
import com.traore.stockmanagement.validator.CategoryValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private List<String> errors;
    private CategoryRepo categoryRepo;
    private CategoryMapper categoryMapper;
    private EnterpriseRepo enterpriseRepo;

    @Override
    public CategoryDTO save(CategoryDTO dto) {
        errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Category dto is not valid {}",dto);
            throw new InvalidEntityException("Catégorie invalide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }

        if (!enterpriseRepo.existsById(dto.getEnterprise().getId())){
            throw new EntityNotFoundException(String.format("Entreprise avec ID {%s} non trouvée", dto.getEnterprise().getId()), ErrorCodes.ENTERPRISE_NOT_FOUND);
        }

        return categoryMapper.fromEntity(
                categoryRepo.save(
                        categoryMapper.toEntity(dto)
                )
        );
    }

    @Override
    public CategoryDTO update(CategoryDTO dto) {
        errors = CategoryValidator.updateValidation(dto);
        if (!errors.isEmpty()){
            for (String error : errors){
                logger.error(error);
            }
            logger.error("Category dto is not valid {}",dto);
            throw new InvalidEntityException("Catégorie invalide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        Category category = categoryRepo.findById(dto.getId()).orElseThrow(
                ()->new EntityNotFoundException(String.format("Catégorie avec ID {%s} non trouvée",dto.getId()), ErrorCodes.CATEGORY_NOT_FOUND)
        );
        category.setName(dto.getName());
        return categoryMapper.fromEntity(
                categoryRepo.save(category)
        );
    }
    @Override
    public CategoryDetailsDTO getCategoryDetail(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                ()->new EntityNotFoundException(String.format("Catégorie avec ID {%s} non trouvée",categoryId), ErrorCodes.CATEGORY_NOT_FOUND)
        );
        return categoryMapper.fromEntityToCategoryDetailsDTO(category);
    }
    @Override
    public void delete(Long categoryId) {
        if (categoryId != null){
            Category category = categoryRepo.findById(categoryId).orElseThrow(
                    ()->new EntityNotFoundException(String.format("Categorie avec ID {%s} non trouvée",categoryId),ErrorCodes.CATEGORY_NOT_FOUND)
            );
            if (category.getProducts().isEmpty()){
                categoryRepo.deleteById(categoryId);
            }else {
                throw new InvalidOperationException(String.format("Catégorie avec ID {%s} est utilisée par au moins un produit",categoryId),ErrorCodes.CATEGORY_ALREADY_IN_USE);
            }

        }else {
            throw new InvalidEntityException("Identifiant de catégorie invalide",ErrorCodes.CATEGORY_NOT_VALID);
        }
    }
}
