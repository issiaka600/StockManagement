package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.category.CategoryDetailsDTO;
import com.traore.stockmanagement.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public CategoryDTO fromEntity(Category category){
        if (category!=null){
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .enterprise(
                            EnterpriseMapper.fromEntityToUpdateDTOStatic(
                                    category.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    public CategoryDetailsDTO fromEntityToCategoryDetailsDTO(Category category){
        if(category !=null){
            return CategoryDetailsDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .products(
                           ProductMapper.fromEntityStatic(
                                   category.getProducts()
                           )
                    )
                    .build();
        }
        return null;
    }

    public List<CategoryDTO> fromEntity(List<Category> categories){
        if(categories != null){
            List<CategoryDTO> dtos = new ArrayList<>();
            for (Category category : categories){
                dtos.add(
                        fromEntity(category)
                );
            }
            return dtos;
        }
        return null;
    }

    public Category toEntity(CategoryDTO dto){
        if (dto!=null){
            return Category.builder()
                    .name(dto.getName().trim())
                    .enterprise(
                            EnterpriseMapper.fromUpdateDTOToEntityStatic(
                                    dto.getEnterprise()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static CategoryDTO fromEntityStatic(Category category){
        if (category!=null){
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        }
        return null;
    }
    public static List<CategoryDTO> fromEntityStatic(List<Category> categories){
        if (categories != null){
            List<CategoryDTO> dtos = new ArrayList<>();
            for (Category category : categories){
                dtos.add(
                        fromEntityStatic(category)
                );
            }
            return dtos;
        }
        return null;
    }
    public static Category toEntityStatic(CategoryDTO dto){
        if (dto!=null){
            return Category.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .build();
        }
        return null;
    }
}
